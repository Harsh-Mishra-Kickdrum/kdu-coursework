function tipcalculator(bill){
    let tipPercentage;

    /**
     * determine the tip percentage based on the bill amount
     */
    if(bill<50){
        tipPercentage = 20;
    }else if(bill >= 50 && bill <= 100){
        tipPercentage = 15;
    }else{
        tipPercentage = 10;
    }

    //calculate the tip
    const tip = bill * (tipPercentage/100);
    return tip;
}

//Bills of the three restaurants
const bill =[140,45,280];

//Array to store the tips for each bill
const tips =[];

//Array to store final paid amount
const finalPaidAmount =[];

//Calculating the tips and the final paid amount
for(let i=0;i<bill.length;i++){
    const tip = tipcalculator(bill[i]);
    tips.push(tip);
    finalPaidAmount.push(tip+bill[i]);
}

console.log("Tips :",tips);
console.log("Final paid amount :",finalPaidAmount);
