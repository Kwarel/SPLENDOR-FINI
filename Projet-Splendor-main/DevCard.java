//Classe DevCard qui permet de modéliser une carte avec toutes ses caractéristique + méthode pour consulter les valeurs (getter).
public class DevCard implements Displayable {
    
    //Déclaration des attributs de la classe DevCard
    private int cardLevel; //Niveau de la carte (1,2,3)
    private int points; //Points de prestiges
    private Resources cost; //Prix de la carte en Resources
    private Resource resourceType; //Bonus de la carte
    
    
    //Constructeur
    /**
     *Constructeur :
     *Initialise les attributs de DevCard
     */
    public DevCard(int cardLevel,int coutDiamond,int coutSapphire, int coutEmerald, int coutRuby, int coutOnyx, int points, Resource resourceType){
        this.cardLevel = cardLevel;
        this.points = points;
        this.cost =  new Resources(coutDiamond,coutSapphire,coutEmerald,coutRuby,coutOnyx);
        this.resourceType = resourceType;
    }
    
    
    //Les accesseurs (getter):
    /**
     * Retourne le niveau de la carte actuel
     * @return cardLevel → Niveau de la carte
     */
    public int getNiveau(){
        return this.cardLevel;
    }
    
    /**
     * Retourne le nombres de points sur la carte
     * @return Points → Points de victoire offert par la carte
     */
    public int getPoints(){
        return this.points;
    }
    
    /**
     * Retourne le coût en ressource de la carte
     * @return costCard → Coût en resource donnée selon l'ordre de Resource
     * (Diamant, Sapphire, Emeraud, Onyx, et Rubis)
     */
    public Resources getCost(){
        return this.cost;
    }
    
    /**
     * Retourne le type de la carte 
     * @return resourceType → Type de resources de la carte
     */
    public Resource getResourceType(){
        return this.resourceType;
    }
    
    
    /* --- Stringers --- */
    public String[] toStringArray(){
        /** EXAMPLE
         * ┌────────┐
         * │①    ♠S│
         * │        │
         * │        │
         * │2 ♠S    │
         * │2 ♣E    │
         * │3 ♥R    │
         * └────────┘
         */
        String pointStr = "  ";
        
        
        
        if(getPoints()>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502"+pointStr+"    "+resourceType.toSymbol()+"\u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};
        
        int i=6;
        for(Resource res : Resource.values()){ //-- parcourir l'ensemble des resources (res)en utilisant l'énumération Resource
            if(getCost().getNbResource(res)>0){
                cardStr[i] = "\u2502"+getCost().getNbResource(res)+" "+res.toSymbol()+"    \u2502";
                i--;
            }
        }
        return cardStr;
    }

    public static String[] noCardStringArray(){
        /** EXAMPLE
         * ┌────────┐
         * │ \    / │
         * │  \  /  │
         * │   \/   │
         * │   /\   │
         * │  /  \  │
         * │ /    \ │
         * └────────┘
         */
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502 \\    / \u2502",
                            "\u2502  \\  /  \u2502",
                            "\u2502   \\/   \u2502",
                            "\u2502   /\\   \u2502",
                            "\u2502  /  \\  \u2502",
                            "\u2502 /    \\ \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};
        
        return cardStr;
    }

    public String toString(){
        String cardStr = "";
        
        
              
        cardStr = getPoints()+"pts, type "+resourceType.toSymbol()+" | coût: ";
        for(Resource res : Resource.values()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            if(getCost().getNbResource(res)>0){
                cardStr += getCost().getNbResource(res)+res.toSymbol()+" ";
            }
        }
        
        return cardStr;
    }
}
