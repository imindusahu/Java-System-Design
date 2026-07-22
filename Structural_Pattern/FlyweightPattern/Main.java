import java.util.*;

// TreeType.java
class TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y){
        System.out.println("Drawing " + name + " tree at (" + x + "," + y + ")");
    }
}

// Tree.java
class Tree {
    private int x;
    private int y;
    private TreeType treeType;

    public Tree(int x, int y, TreeType treeType) {
        //keep changing
        this.x = x;
        this.y = y;

        //they are constant
    //     this.name = name;
    //     this.color = color;
    //     this.texture = texture;
           this.treeType = treeType;
    }

    public void draw() {
        // System.out.println("Drawing  tree at (" + x + "," + y + ") with type " + name);
        treeType.draw(x, y);
    }
}

class treeFactory {
    static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture){
        String key = name + "-" + color + "-" + texture;
        if(!treeTypeMap.containsKey(key)){
            treeTypeMap.put(key, new TreeType(name, color, texture));
        }
        return treeTypeMap.get(key);
    }
}

class Forest {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeType treeType = new TreeType(name, color, texture);
        Tree tree = new Tree(x, y, treeType);
        trees.add(tree);
    }

    public void draw() {
        for(Tree tree : trees) {
            tree.draw();
        }
    }
}

public class Main {
    public static void main(String[] args){
        Forest forest = new Forest();

        // Plant 1 million trees
        for(int i=0; i<100; i++){
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }
        forest.draw();
    }
}