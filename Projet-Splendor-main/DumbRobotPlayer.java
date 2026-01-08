//Importation du package java.util
import java.util.*;
import java.util.Random;

/** La classe DumbRobotPlayer hérite de la classe Player, les attibuts et la plus part des méthodes sont 
 * donc déjà déclarer au préalable dans celle-ci.
 * Elle a pour objectif de simuler un joueur automate qui executera les même méthodes qu'un joueur humain
 * mais de manière prévisible dans un ordre précis.
 */
public class DumbRobotPlayer extends Player {
    
    //Déclaration des attibuts de la class DumbRobotPlayer fait dans la classe mère Player
    
    //Constructeur
    /**
     *Constructeur:
     *Initialise les attibuts de player
     */
    public DumbRobotPlayer(int id,String name){
        super(id,name);
    }
    
    
    //Action executer par le DumbPlayer selon un odre précis selon ce qui lui est possible de faire
    public Action chooseAction(Board board){
        //acheter une carte sur le plateau
        for (int levelCard = 0; levelCard < 3; levelCard++){ //Le niveau de la carte qui incrémente de 1 allant de 0 à 3 (tier 3 à tier 1)
            for (int nbCard = 0; nbCard < 4; nbCard++){ //Le numéro de la carte de 1 à 4 (0 à 3 dans la liste) 
                
                DevCard currentCard = board.getCard(levelCard, nbCard); //On stock la carte que l'on va analyser
                if(currentCard != null && canBuyCard(currentCard)){ // On vérifie si la carte existe bien et si on peut la payer
                    BuyCardAction buyCard = new BuyCardAction(currentCard);
                    return buyCard;
                }
                
            }
        }


        //Prendre deux jetons ressources de même type sur le plâteau
        for (Resource resource : board.getAvailableResources()) {
            if (board.getNbResource(resource) >= 4) {
                PickSameTokensAction twiceTokens = new PickSameTokensAction(resource);
                return twiceTokens; //Retourn les deux jetons identiques
            }
        }


        //acheter des jetons ressources de type différents
        ArrayList<Resource> tokensToBuy = new ArrayList<Resource>();
        for (Resource resource : board.getAvailableResources()) {
            if (tokensToBuy.size() <= 3) {
                tokensToBuy.add(resource); //Liste des trois resources
            }
            return new PickDiffTokensAction(tokensToBuy); //Retourn la liste des trois nouveau jetons du Robot
        }

        
        //Si le RobotPlayer ne peut rien faire alors il saute son tour
        return new PassAction();       
    }


    //jetons a retirer
    public Resources chooseDiscardingTokens(){
        Resources tokensToDiscard = new Resources();
        while(super.getNbTokens()> 10){
            Random random = new Random();
            int rdm = random.nextInt(5);
            if(rdm==0 && super.getNbResource(Resource.DIAMOND) > 0){
                tokensToDiscard.updateNbResource(Resource.DIAMOND,1);
            }
            if(rdm==1 && super.getNbResource(Resource.SAPPHIRE) > 0){
                tokensToDiscard.updateNbResource(Resource.SAPPHIRE,1);
            }
            if(rdm==2 && super.getNbResource(Resource.EMERALD) > 0){
                tokensToDiscard.updateNbResource(Resource.EMERALD,1);
            }
            if(rdm==3 && super.getNbResource(Resource.RUBY) > 0){
                tokensToDiscard.updateNbResource(Resource.RUBY,1);
            }
            if(rdm==4 && super.getNbResource(Resource.ONYX) > 0){
                tokensToDiscard.updateNbResource(Resource.ONYX,1);
            }
        }
        return tokensToDiscard;
    }
}