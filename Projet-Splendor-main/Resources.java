import java.util.ArrayList;
import java.util.HashMap;

public class Resources extends HashMap<Resource, Integer> {

    private final HashMap<Resource, Integer> resources;
    
    /**
     * Créer une HashMap Resources et initialise toutes les resources à 0.
     */
    public Resources(){
        resources = new HashMap<>();
        for (Resource resource : Resource.values()){
            resources.put(resource, 0);
        }
    }
    
    /**
     * Créer une HashMap Resources et initialise toutes les resources à la valeur voulue.
     */
    public Resources(int coutDiamond,int coutSapphire, int coutEmerald, int coutRuby, int coutOnyx) {
        resources = new HashMap<>();
        resources.put(Resource.DIAMOND, coutDiamond);
        resources.put(Resource.SAPPHIRE, coutSapphire);
        resources.put(Resource.EMERALD, coutEmerald);
        resources.put(Resource.RUBY, coutRuby);
        resources.put(Resource.ONYX, coutOnyx);
    }
    
    /**
     * Renvoie le nombre d'une resource.
     */
    public int getNbResource(Resource resource){
        return resources.get(resource);
    }
    
    /**
     * Modifie le nombre d'une resource.
     */
    public void setNbResource(Resource resource, int quantity){
        if (quantity>=0){
            resources.put(resource, quantity);
        }
    }
    
    /**
     * Additionne ou soustrait une quantité d'une resource.
     */
    public void updateNbResource(Resource resource, int quantity){
        if (getNbResource(resource)+quantity>=0){
            resources.put(resource, getNbResource(resource)+quantity);
        }
    }
    
    /**
     * Retourne les types de resources ou il y a + que 0 resource.
     */
    public ArrayList<Resource> getAvailableResources(){
        ArrayList<Resource> availableResources = new ArrayList<>();
        for (Resource resource: Resource.values()){
            if(getNbResource(resource)>0){
                availableResources.add(resource);
            }
        }
        return availableResources;
    }
}