package management;

import management.views.fish.fishJFrame;

import management.models.categories.*;
import management.models.histories.*;
import management.models.details.*;


public class ColdStorage {

    public static void main(String[] args) {
//        
//        Storage obj = new Storage();
//        obj.addStorage("storage1", 0, 50000, "");
//        obj.delStorage("storage1");
//        
//        Provider pObj = new Provider();
//        pObj.addProvider("provider1", "Scammer Indian", "p@gmail.com", "123456", "");
//        
//        InputHistory iObj = new InputHistory();
//        iObj.addInputHistory("input1", "16/3/2024", "16:06", "provider1", "");
//        iObj.delInputHistory("input1");
//        
//
//        InputDetail idObj = new InputDetail();
//        idObj.addInputDetail("input1", 1, "storage1", 50, 5, "");
//        idObj.addInputDetail("input1", 2, "storage1", 100, 10, "");
//        
        OutputHistory oObj = new OutputHistory();
//       oObj.addOutputHistory("output1", "16/3/2024", "17:28", "");
//        
        OutputDetail ohd = new OutputDetail();
        ohd.addOutputDetail("output1", 1, "storage1", 150, 3);
        ohd.delOutputDetail("Output1", 1);
        
        
    }
}
