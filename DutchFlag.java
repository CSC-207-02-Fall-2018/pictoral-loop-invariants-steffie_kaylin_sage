package loopInvariants;
import java.util.concurrent.ThreadLocalRandom;

public class DutchFlag {
    public static final int size = 10;
    enum color {red, white, blue};
    color colors [] = new color [size];
    
    DutchFlag() {
        for (int i = 0; i < size; i++) {
            int val = ThreadLocalRandom.current().nextInt(0, 3);
            
            switch (val) {
            case 0:
                colors[i] = color.red;
                break;
            case 1:
                colors[i] = color.white;
                break;
            case 2:
                colors[i] = color.blue;
                break;
            }
            
        }
    }
    
    public void invariantA() {
        int white1 = 0;
        int blue1 = 0;
        int unproc1 = 0;
        
        while (unproc1 < colors.length) {
            if (colors[unproc1] == color.blue) {
                unproc1++;
            }
            else if (colors[unproc1] == color.white) {
                colors[unproc1] = colors[blue1];
                colors[blue1] = color.white;
                
                unproc1++;
                blue1++;
            }
            else {
                colors[unproc1] = colors[blue1];
                color temp = colors[white1];
                colors[white1] = color.red;
                colors[blue1] = temp;
                
                white1++;
                blue1++;
                unproc1++;
            }
        }
    }
    
    public void invariantB() {
        int white1 = 0;
        int blue1 = colors.length;
        int unproc1 = 0;
        
        while (unproc1 < blue1) {
            if (colors[unproc1] == color.blue) {
                blue1--;
                
                colors[unproc1] = colors[blue1];
                colors[blue1] = color.blue;
            }
            else if  (colors[unproc1] == color.white) {
                unproc1++;
            }
            else {
                colors[unproc1] = colors [white1];
                colors[white1] = color.red;
                
                unproc1++;
                white1++;
            }
        }
    }
    
    public String toString() {
        for (int i = 0; i < colors.length; i++) {
            System.out.print(colors[i] + ", ");
        }
        return "";
    }
    
    public static void main(String [] args) {
        DutchFlag flagtestA = new DutchFlag();
       
        System.out.println(flagtestA);
        
        flagtestA.invariantA();
        
        System.out.println(flagtestA);
        
        DutchFlag flagiestB = new DutchFlag();
        
        System.out.println(flagiestB);
        
        flagiestB.invariantB();
        
        System.out.println(flagiestB);
    }
}

