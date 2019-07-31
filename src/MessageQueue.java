import java.util.ArrayDeque;

public  class MessageQueue <T> {
    private ArrayDeque<Screen> arr;
    public MessageQueue(){
        arr=new ArrayDeque<>();
    }

    public synchronized Screen pop(){
        if(arr.isEmpty())        {return null;}
        else{
            return arr.poll();
        }
    }
    public synchronized void offer(Screen obj){
        arr.offer(obj);

    }

    public synchronized int size(){
        return arr.size();
    }
}
