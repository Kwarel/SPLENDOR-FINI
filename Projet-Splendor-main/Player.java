//Importation des packages java.util
import java.util.ArrayList;
import java.util.List;


//Abstract Class qui représente les différents attibuts et méthodes qu'un joueur peut avoir.
public abstract class Player implements Displayable {
    
    //Déclaration des attibuts de la class Player
    protected int id; //Identifiant du joueur sous forme d'un entier
    protected String name; //Nom du joueur
    protected int points; //Nombre de points de prestiges sous forme d'entier
    protected ArrayList<DevCard> purchasedCards; //Liste de carte de type "DevCard" représentant les cartes acheter par le joueur
    protected Resources resources; //resources du joueur de type Resources (par composition)
    
    
    //Constructeur
    /**
     *Constructeur:
     *Initialise les attibuts de player
     */
    public Player(int id,String name){
        this.id = id;
        this.name = name;
        this.points = 0;
        this.purchasedCards = new ArrayList<DevCard>();
        this.resources = new Resources();
    }
    
    
    //Les accesseurs (getter):
    /**
     * Retourne le nom → name du joueur
     * @return → this.name
     */
    public String getName() {
        return this.name; //Retourne le nom du joueur
    }
    
    /**
     * Retourne le nombre de points actuelle du joueur
     * @return → this.points
     */
    public int getPoints() {
        return this.points; //Retourne le nombre de points du joueur
    }
    
     /**
     * Renvoie le nombre total de jetons que possède le joueur en additionnant les
     * différents éléments de la liste de resource.
     * @return TotalTokens → la somme de tout les jetons du joueur
     */
    public int getNbTokens() {
        int TotalTokens = 0; //Comteur de jeton posséder par le joueur
        //Création d'une boucle qui parcours les resources de la liste et additionne
        //a TotalTokens, la valeur associer à chacune d'elle.
        for(Resource r : Resource.values()){
            TotalTokens += resources.getNbResource(r);
        }
        return TotalTokens; //Retourn la quantité de jeton total posséder par le joueur
    }
    
    /**
     * Renvoie le nombre de carte acheté par le joueur
     */
    public int getNbPurchasedCards() {
        return purchasedCards.size(); //Renvoie la taille de la pile de carte grâce à la size de la liste
    }
    
    /**
     * Renvoie le nombre de ressource acheté pour un type donné
     * @param Resource → le type de jeton dont on veut connaître le nombre 
     */
    public int getNbResource(Resource r) {
        return this.resources.getNbResource(r); //Appelle la méthode de Resources getNbResource
    }
    
    /**
     * Renvoie le nombre de ressource disponible
     */
    public List<Resource> getAvailableResources() {
        return resources.getAvailableResources(); //Liste des resources disponible
    }
    
    //Renvoie les ressources bonus
    public Resources getResourceBonus() {
        Resources resourcesBonus = new Resources();
        for (DevCard card : purchasedCards) {
            resourcesBonus.updateNbResource(card.getResourceType(), 1);
        }
        return resourcesBonus;
    }
    
    /**
     * Renvoie le nombre de ressource d'un type donné présent sur les cartes achetées
     */
    public int getResFromCards(Resource typeResource) {
        int count = 0; //Compteur de la resource demandé sur les cartes
        //Boucle qui regarde chaque carte et incrémente count si de l'élément demandé
        for(DevCard card : purchasedCards){
            if(card.getResourceType() == typeResource){
                count += 1;
            }
        }
        return count; //Renvoie de la valeur total d'un type de resource sur les cartes
    }
    
    //Mutateur (setter):
    /**
     * Prends en paramètre un type de resource et une quantité v
     * Si v>0 alors ajoute cette quantité au type de resource demandé
     * Si v<0 alors supprime cette quantité au type de resource demandé
     * En cas de suppression de quantité, si v absolue est superieur à la quantité restante,
     * alors le reste ne peut pas être inferieur à 0.
     * Cette méthode est faite par composition avec la méthode préexistente de Resources
     */
    public void updateNbResource(Resource type,int v) {
        resources.updateNbResource(type,v);
    }
    
    /**
     * Incrémente le nombre de points de prestige acquis par le joueur
     */
    public void updatePoints(DevCard card) {
        this.points += card.getPoints();
    }
    
    /**
     * Permet d'ajouter la carte séléctionner pour achat à la liste des cartes acheté par le joueur
     */
    public void addPurchasedCard(DevCard card) {
        this.purchasedCards.add(card); //Utilisation de la méthode add pour rajouter un objet de type DevCard à la liste de carte posséder par le joueur
    }
    
    /**
     * Permet de vérifier si le solde de ressources est assez élevée pour acheter
     * la carte séléctionné
     * Si jetonsBonus (resource donné par les cartes) + jeton du joueur > cost, 
     * alors le programme renvoie True, la carte peut être achetée, sinon le programme
     * renvoie False
     */
    public boolean canBuyCard(DevCard card) {
        //Boucle qui va prendre chaque élément de Resource et va comparer le
        // nombre de jetonBonus + jetonJoueur au prix de ce type de resource de la carte
        for (Resource r : Resource.values()){
            int posseder = getResFromCards(r) + getNbResource(r); //Resource d'un type du joueur
            int requis = card.getCost().getNbResource(r); //Resource d'un type requis pour l'achat de la carte           
            if (posseder < requis){
                return false;
            }
        }
        return true;
    }
    
    
    /* --- Stringers --- */ 
    public String[] toStringArray(){
        /** EXAMPLE. The number of resource tokens is shown in brackets (), and the number of cards purchased from that resource in square brackets [].
         * Player 1: Camille
         * ⓪pts
         * 
         * ♥R (0) [0]
         * ●O (0) [0]
         * ♣E (0) [0]
         * ♠S (0) [0]
         * ♦D (0) [0]
         */
        String pointStr = " ";
        String[] strPlayer = new String[8];
        
        if(points>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }else{
            pointStr = "\u24EA";
        }

        
        strPlayer[0] = "Player "+(id+1)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        
        for(Resource res : Resource.values()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        return strPlayer;
    }
    
    
    //Redéfinition obligatoire des méthodes :
    public abstract Action chooseAction(Board board);
    public abstract Resources chooseDiscardingTokens();
}