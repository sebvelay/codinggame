package codinggame.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 */

class Simulation {

    private static int DEPTH = 1;
    static long start;
    static Point LAST_KNOW_POSITION_0;
    static Point LAST_KNOW_POSITION_1;

    static Node play(List<Unit> allUnits) {
        start = System.currentTimeMillis();

        List<Node> nodes = getAllNodesForPlayer(allUnits);
        Node best = getBest(nodes);
        System.err.println("end at : " + (System.currentTimeMillis() - start));

        return best;
    }


    static List<Node> getAllNodesForPlayer(List<Unit> allUnits) {
        List<Node> nodes = new ArrayList<>();
        for(Unit myUnit : allUnits){

                if(!myUnit.isEnnemy){
                    nodes.addAll(myUnit.listActions());
                }

        }
        return nodes;
    }

    static Node getBest(List<Node> nodes){
        //nodes.removeIf(n->n.caseToMove.occuped || n.caseToBuild.occuped);
        nodes.removeIf(n->n.action.type!="PUSH&BUILD" && n.caseToMove.occuped);


        Node best = nodes.get(0);
        for(Node n : nodes){
            if(n.score>best.score){
                best=n;
            }
        }
        return best;
    }


}

class Evaluation{

    public static List<Node> filter(int numberToKeep,List<Node> nodes){
        if(nodes.size()<=numberToKeep){
            return nodes;
        }
        int i=0;
        List<Node> keepedNode = new ArrayList<>();
        while(i<numberToKeep){
            Node best = Simulation.getBest(nodes);
            keepedNode.add(best);
            nodes.removeIf(n->n.equals(best));
            i++;
        }
        return keepedNode;
    }



}

/**
 * représente une action avec ces caractéristique
 */
class Node {

    public final Action action;
    public final Case caseToMove;
    public final Case caseToBuild;
    public int score = 0;
    public final Unit unit;
    public List<Node> childsNode = new ArrayList<>();
    public Grille grilleAfterAction;


    Node(Action action, Case caseToMove, Case caseToBuild, Unit u) {
        this.action = action;
        this.caseToMove = caseToMove;
        this.caseToBuild = caseToBuild;
        this.unit = u;

        //si je peux aller sur une case de niveau 3 je gagne 100 points
        if (caseToMove.hauteur == 3) {
            this.score = 1000;
        }
        //si je monte par rapport ou je suis je gagne 50 points
        if(caseToMove.hauteur>u.myCase.hauteur){
            this.score += 500;
            if(caseToMove.hauteur==2){
                this.score+=2;
            }
            if(caseToMove.hauteur==1){
                this.score+=1;
            }

        }
        if(caseToMove.hauteur<u.myCase.hauteur){
            this.score -= 500;
        }
        if(caseToBuild.hauteur==1 && caseToMove.hauteur>1){
            this.score += 100;
        }
        //
        if (caseToBuild.hauteur == 3) {
            this.score -= 900;
        }
        if (caseToBuild.hauteur == 0 && caseToMove.hauteur == 0) {
            this.score += 200;
        }
        if(caseToMove.hauteur==3 && caseToBuild.hauteur==2){
            this.score += 300;
        }
        if (caseToMove.hauteur < u.myCase.hauteur) {
            this.score -= 100;
        }

        if (action.type.equals("PUSH&BUILD") && caseToMove.hauteur > 1) {
            this.score += 15000;
            if (caseToBuild.hauteur == 0) {
                this.score += 500;
            } else if (caseToBuild.hauteur == 1) {
                this.score += 200;
            }
        }

        //on compte le nombre de move actuel
        int currentMove = u.grille.countAvailableMoveForUnit(u.isEnnemy);

        //si le nombre de move après est plus grand, on veut bouger

        //simulation
        this.grilleAfterAction = u.grille.simulateAction(action);
        int countMoveAfter = grilleAfterAction.countAvailableMoveForUnit(u.isEnnemy);
        this.score += countMoveAfter;
        if (currentMove < countMoveAfter) {
            this.score -= 10000;
        }else{
            this.score += 10000;
        }

        //si est est proche d'un ennemy
        if(action.type.equals("MOVE&BUILD")){
            for(Unit otherUnit : grilleAfterAction.units){
                int otherX = otherUnit.point.x;
                int otherY = otherUnit.point.y;
                if(otherX==-1){
                    if(otherUnit.number==0 && Simulation.LAST_KNOW_POSITION_0!=null){
                        otherX=Simulation.LAST_KNOW_POSITION_0.x;
                        otherY=Simulation.LAST_KNOW_POSITION_0.y;
                    }else if( otherUnit.number == 1 && Simulation.LAST_KNOW_POSITION_1!=null){
                        otherX=Simulation.LAST_KNOW_POSITION_1.x;
                        otherY=Simulation.LAST_KNOW_POSITION_1.y;
                    }
                }

                if (otherUnit.isEnnemy != action.unit.isEnnemy && otherX != -1 && otherY != -1) {
                    if(otherUnit.point.getDistance(caseToMove.point) ==1){
                        this.score-=1000;
                    }
                }
            }

        }



    }

    void deeper(int depth) {
        if (depth > 0) {
            //on récupère les unit adverse
            for (Unit otherUnit : grilleAfterAction.units) {
                if (otherUnit.isEnnemy != action.unit.isEnnemy && otherUnit.point.x != -1 && otherUnit.point.y != -1) {
                    List<Node> nodesEnnemy = otherUnit.listActions();
                    childsNode.addAll(nodesEnnemy);
                }
            }
        }
    }

}





class Point{
    public final int x;
    public final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getDistance(Point p){
        return Math.max(Math.abs(p.x-x),Math.abs(p.y-y));
    }
}

class Unit {
    public Point point;
    public Case myCase;
    public final Grille grille;
    public final int number;
    public final boolean isEnnemy;

    public Unit(int unitX, int unitY, Grille grille, int number, boolean isEnnemy) {
        if (isEnnemy) {
            if (unitX != -1) {
                if (number == 0) {
                    Simulation.LAST_KNOW_POSITION_0 = new Point(unitX, unitY);
                } else if (number == 1) {
                    Simulation.LAST_KNOW_POSITION_1 = new Point(unitX, unitY);
                }
            }
        }

        this.point = new Point(unitX, unitY);


        this.myCase = grille.getCase(unitX, unitY);
        this.grille = grille;
        this.number = number;
        this.isEnnemy = isEnnemy;
    }


    public Unit clone() {
        return new Unit(this.point.x, this.point.y, this.grille, this.number, this.isEnnemy);
    }

    public void display() {
        System.err.println(point.x + " " + point.y);
    }

    public Point move(String move) {
        int x = point.x;
        int y = point.y;
        if (move.contains("N")) {
            y--;
        }
        if (move.contains("S")) {
            y++;
        }
        if (move.contains("E")) {
            x++;
        }
        if (move.contains("W")) {
            x--;
        }
        return new Point(x, y);
    }

    public List<Node> listActions() {
        List<Node> nodes = new ArrayList<>();
        //pour chaque coup
        for (Move move : grille.availableMoveArroundPoint(this.point)) {
            //on simule les directions
            Point pointMove = this.move(move.direction);

            //cas 2 la case de move est occupé par un ennemi, si on le pousse on peut construire dessus
            if (move.target.occuperByEnnemy && move.target.hauteur >= 0 && move.target.occuped) {

                //on récupère les direction possible pour la direction où on pousse
                List<String> directionsToPush = Direction.closestDirection(move.direction);
                for (String directionToPush : directionsToPush) {
                    //il faut vérifier si on peut le pousser dans cette direction
                    Case caseAfterMove = grille.getCaseAfterMoveDir(move.target, directionToPush);
                    if (caseAfterMove != null && caseAfterMove.hauteur<=move.target.hauteur && !caseAfterMove.occuped && caseAfterMove.hauteur != -1 && caseAfterMove.hauteur != 4) {
                        Action action = new Action("PUSH&BUILD", number, move.direction, directionToPush, this);
                        Node node = new Node(action, move.target, caseAfterMove, this);
                        nodes.add(node);
                    }
                }


            }

            //on regarde les builds possibles
            List<Move> builds = grille.availableMoveArroundPoint(pointMove);
            //avec une direction et un build on construit une action
            for(Move build : builds) {
                //cas 1 la case de move est libre, et à une hauteur atteignable
                if ((myCase.hauteur - move.target.hauteur >= -1) && (!build.target.occuped || occupedByMe(build.target))) {
                    Action action = new Action("MOVE&BUILD", number, move.direction, build.direction, this);
                    Node node = new Node(action, move.target, build.target, this);
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }


    public boolean occupedByMe(Case c){
        if(c.occuped && c.point.y==myCase.point.y && c.point.x==myCase.point.x){
            return true;
        }
        return false;
    }


}

class Move{
    public final String direction;
    public final Case target;

    Move(String direction, Case target) {
        this.direction = direction;
        this.target = target;
    }
}

class Action {
    public final String type;
    public final int index;
    public final String dir1;
    public final String dir2;
    public final Unit unit;

    Action(String type, int index, String dir1, String dir2,Unit u) {
        this.type = type;
        this.index = index;
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.unit = u;
    }

    public void displayDebug() {
        System.err.println(toString());
    }

    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString(){
        return type + " " + index + " " + dir1 + " " + dir2;
    }
}

class Grille {
    final int size;
    List<Case> cases = new ArrayList<>();
    List<Unit> units = new ArrayList<>();

    Grille(int size, String row) {
        this.size = size;
        int tot = 0;
        int x = 0;
        int y = 0;
        while (tot < size * size) {
            int hauteur = -1;
            if ((!(row.charAt(tot) == '.'))) {
                hauteur = Integer.parseInt(row.substring(tot, tot + 1));
            }
            Case aCase = new Case(x, y, hauteur);
            cases.add(aCase);
            x++;
            if (x >= size) {
                y++;
                x = 0;
            }
            tot++;
        }
    }

    private Grille(int size,List<Case> cases,List<Unit> units){
        this.size=size;
        this.cases = cases.stream().map(Case::clone).collect(Collectors.toList());
        this.units = units.stream().map(Unit::clone).collect(Collectors.toList());
    }

    public Grille clone(){

        return new Grille(this.size,this.cases,this.units);
    }

    void addUnit(Unit unit){
        units.add(unit);
        for(Case c:this.cases){
            if(c.point.y==unit.point.y && c.point.x == unit.point.x){
                c.occuped=true;
                if(unit.isEnnemy){
                    c.occuperByEnnemy=true;
                }
            }
        }
    }

    List<Case> getCasesArround(int x, int y) {
        ArrayList<Case> casesArround = new ArrayList<>();
        for (Case c : cases) {
            if (!(c.point.x == x && c.point.y == y) && (c.point.x >= x - 1 && c.point.x <= x + 1) && (c.point.y >= y - 1 && c.point.y <= y + 1)) {
                casesArround.add(c);
            }

            //hack pour sortir plus vite
            if (casesArround.size() == 8) {
                break;
            }
        }
        return casesArround;
    }

    Case getCase(int x,int y){
        for(Case c:cases){
            if(c.point.y==y && c.point.x==x){
                return c;
            }
        }
        return null;
    }

    Case getCaseAfterMoveDir(Case depart, String direction){
        int afterX=depart.point.x;
        int afterY=depart.point.y;
        if(direction.contains("N")){
            afterY--;
        }
        else if(direction.contains("S")){
            afterY++;
        }
        if(direction.contains("E")){
            afterX++;
        }
        else if(direction.contains("W")){
            afterX--;
        }

        return getCase(afterX,afterY);
    }

    Grille simulateAction(Action action){
        Grille clone = this.clone();
        if(action.type.equals("MOVE&BUILD")){
            //on augmente la hauteur a l'arrivée
            Case caseAfterMove = clone.getCaseAfterMoveDir(action.unit.myCase, action.dir1);
            Case caseToBuild = clone.getCaseAfterMoveDir(caseAfterMove,action.dir2);
            caseToBuild.addHauteur();
            //je move
            for(Unit u : clone.units){
                if((u.point.x == action.unit.point.x) &&(u.point.y == action.unit.point.y)){
                    u.myCase=caseAfterMove;
                    u.point=new Point(caseAfterMove.point.x,caseAfterMove.point.y);
                }
            }
        }else if(action.type.equals("PUSH&BUILD")){
            Case caseAfterMoveDir = clone.getCaseAfterMoveDir(action.unit.myCase, action.dir1);
            caseAfterMoveDir.addHauteur();
        }


        return clone;
    }

    public List<Move> availableMoveArroundPoint(Point p) {
        List<Case> target = this.getCasesArround(p.x, p.y);
        List<Move> moves = new ArrayList<>();
        for (Case c : target) {
            if (c.hauteur != -1 && c.hauteur != 4) {
                String move = "";
                if (c.point.y < p.y) {
                    move += "N";
                }
                if (c.point.y > p.y) {
                    move += "S";
                }
                if (c.point.x < p.x) {
                    move += "W";
                }
                if (c.point.x > p.x) {
                    move += "E";
                }
                Move aMove = new Move(move, c);
                moves.add(aMove);
            }

        }
        return moves;
    }

    public int countAvailableMoveForUnit(boolean isEnnemy) {
        int availableMove = 0;
        for (Unit u : this.units) {
            if (u.isEnnemy == isEnnemy) {
                availableMove += availableMoveArroundPoint(u.point).size();
            }

        }
        return availableMove;
    }

}

class Case {
    final Point point;
    int hauteur;
    boolean occuped;
    boolean occuperByEnnemy;

    Case(int x, int y, int hauteur) {
        this.point = new Point(x, y);
        this.hauteur = hauteur;
    }

    public Case clone() {
        return new Case(this.point.x, this.point.y, this.hauteur);
    }

    public void addHauteur() {
        this.hauteur = hauteur + 1;
    }


}

class Direction {

    static Map<String, List<String>> closeDirection = new HashMap<String, List<String>>() {
        {
            put("S", Arrays.asList("S", "SE", "SW"));
            put("N", Arrays.asList("N", "NE", "NW"));
            put("NE", Arrays.asList("NE", "N", "E"));
            put("NW", Arrays.asList("NW", "N", "W"));
            put("SE", Arrays.asList("SE", "S", "E"));
            put("SW", Arrays.asList("SW", "S", "W"));
            put("E", Arrays.asList("E", "SE", "NE"));
            put("W", Arrays.asList("W", "SW", "NW"));
        }
    };

    static List<String> closestDirection(String direction) {
        return closeDirection.get(direction);
    }
}