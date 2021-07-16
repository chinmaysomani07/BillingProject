package com.example.glassmaterial.controller;

import com.example.glassmaterial.dao.MaterialDao;
import com.example.glassmaterial.model.Billing;
import com.example.glassmaterial.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/material")
//@CrossOrigin(origins = "http://localhost:4200")           //comment this out when using postman and uncomment ot while using localhost
public class MaterialController {

    @Autowired
    private MaterialDao materialDao;
    double Bill = 0.0d;

    @GetMapping("/getmaterials")
    public List<Material> getAllMaterials(){
        List<Material> materialList = materialDao.getAllMaterials();
        System.out.println(materialList);
        return materialList;
    }

    @GetMapping("/getmaterialname")
    public List<String> getAllMaterialNameForDropDown(){
        List<String> materialNameList=materialDao.getAllMaterialName();
        return  materialNameList;
    }

    //@CrossOrigin(origins = "http://localhost:4200")       //comment this out when using postman and uncomment ot while using localhost
    @PostMapping("/getbill")
    //@GetMapping("/generatebill")
    public double getBill(@RequestBody List<Billing> billing){


        ArrayList<Double> allLengths=new ArrayList<>();
        ArrayList<Double> allBreadths=new ArrayList<>();
        ArrayList<Double> sutOfLength=new ArrayList<>();
        ArrayList<Double> sutOfBreadth=new ArrayList<>();
        ArrayList<Double> numberOfPieces=new ArrayList<>();
        ArrayList<Double> materialCostList = new ArrayList<>();
        ArrayList<Double> workCostList = new ArrayList<>();
        ArrayList<Double> finalLengths=new ArrayList<>();
        ArrayList<Double> finalBreadths=new ArrayList<>();
        ArrayList<Double> costOfMaterialsList=new ArrayList<>();
        ArrayList<Double> costOfWorkList=new ArrayList<>();


        for(int i=0;i<billing.size();i++) {
            double quantity = billing.get(i).getQuantity();

            numberOfPieces.add(quantity);
            System.out.println("Quantity is:" + quantity);
            System.out.println("Material is:" + billing.get(i).getMaterial());
            System.out.println("size in mm is :" + billing.get(i).getSizeinmm());


            allLengths.add(billing.get(i).getLength());
            allBreadths.add(billing.get(i).getBreadth());
            sutOfLength.add(billing.get(i).getSut1());
            sutOfBreadth.add(billing.get(i).getSut2());

            double priceOfMaterial = materialDao.getPriceOfMaterial(billing.get(i).getMaterial(), billing.get(i).getSizeinmm());
            double priceOfWork=materialDao.getPriceOfWork(billing.get(i).getWork().getType());
            String typeOfWork=billing.get(i).getWork().getType();
            System.out.println("Type of work is:" +typeOfWork);


            materialCostList.add(priceOfMaterial);
            workCostList.add(priceOfWork);

//            System.out.println("Work type is :" +billing.get(i).getWork().getType());
//            System.out.println("Price of work is :" +priceOfWork);
//            System.out.println("The Price is:" + priceOfMaterial);

        }
        System.out.println("Length List:" +allLengths);
        System.out.println("Breadth List:" +allBreadths);
        System.out.println("Material Cost List:" +materialCostList);
        System.out.println("Work Cost List:" +workCostList);

        for(int i=0;i<allLengths.size();i++){
            double modLength=allLengths.get(i) % 12;
            double remLength=modLength/12.0d;
            double divideLength=allLengths.get(i)/12.0d;

            double tempLength=0.0d;
            if(remLength==0.0d && sutOfLength.get(i)==0){
                tempLength=tempLength;
            }else if(remLength==0.0d && sutOfLength.get(i)>0){
                tempLength=0.25d;
            }else if(remLength>0.00d && remLength<0.25d){
                tempLength=0.25;
            }else if(remLength==0.25){
                if(sutOfLength.get(i)>0){
                    tempLength=0.5;
                }else{
                    tempLength=0.25;
                }
            }else if(remLength>0.25d && remLength<0.50d){
                tempLength=0.5d;
            }else if(remLength==0.5d){
                if(sutOfLength.get(i)>0){
                    tempLength=0.75d;
                }else{
                    tempLength=0.5d;
                }
            }else if(remLength>0.50d && remLength<0.75d){
                tempLength=0.75d;
            }else if(remLength==0.75){
                if(sutOfLength.get(i)>0){
                    tempLength=1.0d;
                }else{
                    tempLength=0.75d;
                }
            }else if(remLength>0.75){
                tempLength=1.0d;
            }

            double finalLength=Math.floor(divideLength) + tempLength;
            System.out.println("Final Length: " +finalLength);
            finalLengths.add(finalLength);


            double modBreadth=allBreadths.get(i) % 12;
            double remBreadth=modBreadth/12.0d;
            double divideBreadth=allBreadths.get(i)/12.0d;

            double tempBreadth=0.0d;
            if(remBreadth==0.0d && sutOfBreadth.get(i)==0){
                tempBreadth=tempBreadth;
            }else if(remBreadth==0.0d && sutOfBreadth.get(i)>0){
                tempBreadth=0.25;
            }else if(remBreadth>0.00d && remBreadth<0.25d){
                tempBreadth=0.25;
            }else if(remBreadth==0.25){
                if(sutOfBreadth.get(i)>0){
                    tempBreadth=0.5;
                }else{
                    tempBreadth=0.25;
                }
            }else if(remBreadth>0.25d && remBreadth<0.5d){
                tempBreadth=0.5d;
            }else if(remBreadth==0.5d){
                if(sutOfBreadth.get(i)>0){
                    tempBreadth=0.75d;
                }else{
                    tempBreadth=0.5d;
                }
            }else if(remBreadth>0.50d && remBreadth<0.75d){
                tempBreadth=0.75d;
            }else if(remBreadth==0.75){
                if(sutOfBreadth.get(i)>0){
                    tempBreadth=1.0d;
                }else{
                    tempBreadth=0.75d;
                }
            }else if(remBreadth>0.75){
                tempBreadth=1.0d;
            }

            double finalBreadth=Math.floor(divideBreadth) + tempBreadth;
            System.out.println("Final Breadth: " +finalBreadth);
            finalBreadths.add(finalBreadth);
        }

        for(int i=0;i<finalLengths.size();i++){
            double materialBill=0.0d;
            double workBill=0.0d;
            String typeOfWork=billing.get(i).getWork().getType();
            System.out.println("Type of work is:" +typeOfWork);
            materialBill=finalLengths.get(i)*finalBreadths.get(i)*numberOfPieces.get(i);
            if(typeOfWork.equalsIgnoreCase("polish")){
                workBill=(2*finalLengths.get(i))+(2*finalBreadths.get(i));
                workBill=workBill*numberOfPieces.get(i);
            }else{
                workBill=finalLengths.get(i)*finalBreadths.get(i)*numberOfPieces.get(i);
            }

            costOfMaterialsList.add(materialBill);
            costOfWorkList.add(workBill);
        }

        System.out.println("Cost of Materials List:" +costOfMaterialsList);
        System.out.println("Cost of Work List:" +costOfWorkList);

        double temp1=0.0d;
        double temp2=0.0d;
        for(int i=0;i<costOfMaterialsList.size();i++){
            temp1+=costOfMaterialsList.get(i)*materialCostList.get(i);
            temp2+=costOfWorkList.get(i)*workCostList.get(i);
        }

        Bill=temp1+temp2;

        System.out.println("Your total Bill is:" +Bill);
       // return "Your Total Bill is:" +Bill;
        return Bill;
    }

    @GetMapping("/generatebill")
    public double generateBill(){
        System.out.println("generateBill() method called");
        return Bill;
    }

    @GetMapping("/getsizeinmm/{material}")
    public List<Double> getSizesInmm(@PathVariable String material){
        List<Double> listOfMM=new ArrayList<>();
        listOfMM=materialDao.getSizeInmm(material);
        return listOfMM;
    }
}
