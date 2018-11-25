import java.util.ArrayList;
import java.util.List;
public class bruteforce{

    private static List<Food> selectedBeverage = new ArrayList<>();
    private static List<Food> selectedMainDish = new ArrayList<>();
    private static List<Food> selectedFruit = new ArrayList<>();
    private static List<Food> selectedSnack = new ArrayList<>();

    private static List<Food> optimalBeverage = new ArrayList<>();
    private static List<Food> optimalMainDish = new ArrayList<>();
    private static List<Food> optimalFruit = new ArrayList<>();
    private static List<Food> optimalSnack = new ArrayList<>();
    private static List<Food> optimal = new ArrayList<>();
    private static boolean range(double i, double LB, double UB){
        return i >= LB && i <= UB;
    }
    private static int getDayPrice(){
        int price = 0;
        for (Food f : selectedBeverage) {
            price += f.getPrice();
        }
        for (Food d : selectedMainDish) {
            price += d.getPrice();
        }
        for (Food d : selectedFruit) {
            price += d.getPrice();
        }
        for (Food d : selectedSnack) {
            price += d.getPrice();
        }
        return price;
    }
    private static boolean checkConstraints() {
        double protein = 0.0;
        double carb = 0.0;
        double fat = 0.0;
        double sugar = 0.0;
        double calcium = 0.0;
        double fiber = 0.0;
        for (Food f : selectedBeverage) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (Food f : selectedMainDish) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (Food f : selectedFruit) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }
        for (Food f : selectedSnack) {
            protein += f.getProtein();
            carb += f.getCarb();
            fat += f.getFat();
            sugar += f.getSugar();
            fiber += f.getFiber();
            calcium += f.getCalcium();
        }

        // if violate any constraint - return false
        return (range(protein, NutritionPlan.lb_protein, NutritionPlan.ub_protein))
                && (range(carb, NutritionPlan.lb_carb, NutritionPlan.ub_carb))
                && (range(fat, NutritionPlan.lb_fat, NutritionPlan.ub_fat))
                && (range(sugar, NutritionPlan.lb_sugar, NutritionPlan.ub_sugar))
                && (fiber < NutritionPlan.fiber)
                && (range(calcium, NutritionPlan.lb_calcium, NutritionPlan.ub_calcium));
    }
    public static void bruteForceAlgo(){
        NutritionPlan.getInput();
        NutritionPlan.setFood();

        //System.out.println(FoodData.getAllFoods());

        selectedBeverage.clear();
        selectedMainDish.clear();
        selectedFruit.clear();
        selectedSnack.clear();

        optimalBeverage.clear();
        optimalMainDish.clear();
        optimalFruit.clear();
        optimalSnack.clear();
        optimal.clear();

        List<Food> beverage = FoodData.getBeverage();
        List<Food> maindish = FoodData.getMainDish();
        List<Food> fruit = FoodData.getFruit();
        List<Food> snack = FoodData.getSnack();
        int beverageCount = 0;
        int maindishCount = 0;
        int fruitCount = 0;
        int snackCount = 0;
        List<String> beverageMeal = new ArrayList<>();
        List<String> maindishMeal = new ArrayList<>();
        List<String> fruitMeal = new ArrayList<>();
        List<String> snackMeal = new ArrayList<>();

        //select 2 beverage
        for (int i = 0; i < beverage.size(); i++) {
            for (int j = i + 1; j < beverage.size(); j++) {
                beverageCount++;
                beverageMeal.add(beverage.get(i).getBinary() + beverage.get(j).getBinary());
            }
        }
        //select 3 maindish
        for (int i = 0; i < maindish.size(); i++)
            for (int j = i + 1; j < maindish.size(); j++)
                for (int k = j + 1; k < maindish.size(); k++) {
                    maindishCount++;
                    maindishMeal.add(maindish.get(i).getBinary() + maindish.get(j).getBinary() + maindish.get(k).getBinary());
                }
        //select 1 fruit
        for (Food aFruit : fruit) {
            fruitCount++;
            fruitMeal.add(aFruit.getBinary());
        }
        //select 1 snack
        for (Food aSnack : snack) {
            snackCount++;
            snackMeal.add(aSnack.getBinary());
        }

        System.out.println(beverageCount + " " + maindishCount + " " + fruitCount + " " + snackCount);

        double price;
        double minPrice = 3000;
        for (int i = 0; i < beverageCount; i++) {
            System.out.print("*");
            selectedBeverage.clear();
            selectedMainDish.clear();
            selectedFruit.clear();
            selectedSnack.clear();
            for(int j = 0; j < maindishCount; j++){
                System.out.print("#");
                for(int k = 0 ; k < fruitCount; k++){
                    System.out.print("+");
                    for(int m = 0; m < snackCount; m++){
                        System.out.print("-");
                        Food b1;
                        Food b2;
                        Food m1;
                        Food m2;
                        Food m3;
                        Food f1;
                        Food s1;
                        b1 = FoodData.matchBeverage(beverageMeal.get(i).substring(0, 4));
                        b2 = FoodData.matchBeverage(beverageMeal.get(i).substring(4, 8));
                        m1 = FoodData.matchMaindish(maindishMeal.get(j).substring(0, 5));
                        m2 = FoodData.matchMaindish(maindishMeal.get(j).substring(5, 10));
                        m3 = FoodData.matchMaindish(maindishMeal.get(j).substring(10, 15));
                        f1 = FoodData.matchFruit(fruitMeal.get(k).substring(0, 4));
                        s1 = FoodData.matchSnack(snackMeal.get(m).substring(0, 4));

                        selectedBeverage.add(b1);
                        selectedBeverage.add(b2);
                        selectedMainDish.add(m1);
                        selectedMainDish.add(m2);
                        selectedMainDish.add(m3);
                        selectedFruit.add(f1);
                        selectedSnack.add(s1);

                        //check constraints
                        if(checkConstraints()) {
                            price = getDayPrice();
                            if (price < minPrice) {
                                optimalBeverage.clear();
                                optimalMainDish.clear();
                                optimalFruit.clear();
                                optimalSnack.clear();

                                minPrice = price;
                                optimalBeverage.add(b1);
                                optimalBeverage.add(b2);
                                optimalMainDish.add(m1);
                                optimalMainDish.add(m2);
                                optimalMainDish.add(m3);
                                optimalFruit.add(f1);
                                optimalSnack.add(s1);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Minimum is " + minPrice);
        if (optimalBeverage.size() > 0) {
            System.out.println("2 Beverages: " + optimalBeverage.get(0).getName() + optimalBeverage.get(1).getName()
                    + "\n3 Main Dishes: " + optimalMainDish.get(0).getName() + " " + optimalMainDish.get(1).getName()
                    + " " + optimalMainDish.get(2).getName()
                    + "\n 1 Fruit: " + optimalFruit.get(0).getName() + "\n 1 Snack: " + optimalSnack.get(0).getName());
        } else {
            System.out.println("There is no available menu for you");
        }
    }
}




