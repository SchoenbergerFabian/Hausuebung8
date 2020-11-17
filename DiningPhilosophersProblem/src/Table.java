public class Table {
    private final Dagger[] daggers;

    public Table(int numberOfDaggers){
        daggers = initializeDaggers(numberOfDaggers);
    }

    private Dagger[] initializeDaggers(int numberOfDaggers){
        Dagger[] daggers = new Dagger[numberOfDaggers];

        for(int index = 0; index < daggers.length; index++){
            daggers[index] = new Dagger();
        }

        return daggers;
    }

    public Dagger getLeftDagger(int index){
        index = index-1;
        if(index==-1){
            index = daggers.length-1;
        }

        return daggers[index];
    }

    public Dagger getRightDagger(int index){
        return daggers[index];
    }
}
