import java.util.Scanner;
import java.util.Random;

public class BattleShips {
    public static void main(String[] args) {
        String[][] OceanMap = new String[10][10];
        /*for (int i=0;i<OceanMap.length;i++) {
            for (int j=0;j<OceanMap[i].length;j++) {
                OceanMap[i][j] = " " ;
            }
        }
        Display(OceanMap);*/
        Intro(OceanMap);
        System.out.println();
        DeployPlayersShips(OceanMap);
        System.out.println();
        Display(OceanMap);
        System.out.println();
        DeployComputersShips(OceanMap);
        Display(OceanMap);
        Battle(OceanMap);
        System.out.println();
        //Count(OceanMap);
    }

    public static void Intro(String a[][]) {
        System.out.println("**** Welcome to Battle Ships game ****\n");
        System.out.println("Right now, the sea is empty.\n");
        for (int i=0;i<a.length;i++) {
            for (int j=0;j<a[i].length;j++) {
                a[i][j] = " " ;
            }
        }
        Display(a);
    }

    public static void DeployPlayersShips(String a[][]) {
        Scanner input = new Scanner(System.in);
        int x,y,z=0;
        System.out.println("Deploy your ships: ");
        for (int i=1;i<=5;i++) {
            do{
                System.out.print("Enter X coordinate for your " + i + ". ship: ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your " + i + ". ship: ");
                y = input.nextInt();
                if ((x<10)&&(y<10)) {
                    if (!a[x][y].equals("1")) {
                        a[x][y] = "1";
                    } else {
                        z = 1;
                    }
                }
            } while ((z==1)||(x>10)||(y>10));
        }
    }

    public static void DeployComputersShips(String a[][]) {
        Random rand = new Random();
        int x=0,y=0,z=0;
        System.out.println("Computer is deploying ships: ");
        for (int i=1;i<=5;i++) {
            do{
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                if ((!a[x][y].equals("1"))&&(!a[x][y].equals("2"))) {
                    a[x][y] = "2";
                    z=0;
                } else {
                    z = 1;
                }
            } while (z==1);
            System.out.println(i + ". ship Deployed");
        }
        System.out.println("---------------------");
    }

    public static void Battle (String a[][]) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        int x=0,y=0,z=0,u=0,PlayerShips,ComputerShips;
        do {
            PlayerShips=0;
            ComputerShips=0;
            System.out.println("YOUR TURN");

            do {
                System.out.print("Enter X coordinate: ");
                x = input.nextInt();
                System.out.print("Enter the Y coordinate: ");
                y = input.nextInt();
                if ((x < 10) && (y < 10)) {
                    if (a[x][y].equals("2")) {
                        System.out.println("Boom! You sunk the ship!");
                        a[x][y] = "!";
                        //z=0;
                    } else if (a[x][y].equals("1")) {
                        System.out.println("Oh no, you sunk your own ship :(");
                        a[x][y] = "x";
                        //z=0;
                    } else {
                        System.out.println("Sorry, you missed");
                        a[x][y] = "-";
                    }
                    z=0;
                } else {
                    z = 1;
                }
            } while (z == 1);

            System.out.println("COMPUTER'S TURN");
            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                if ((!a[x][y].equals("x")) && (!a[x][y].equals("!")) && (!a[x][y].equals("4"))) {
                    if (a[x][y].equals("1")) {
                        System.out.println("The Computer sunk one of your ships!");
                        a[x][y] = "x";
                    } else if (a[x][y].equals("2")) {
                        System.out.println("The Computer sunk one of its own ships");
                        a[x][y] = "!";
                    } else {
                        System.out.println("Computer missed");
                        a[x][y] = "4";
                    }
                    u=0;
                } else {
                    u = 1;
                }
            } while (u == 1);

            for (int i = 0; i < a.length; i++) {
                for (int j = 0;j < a[i].length; j++) {
                    if (a[i][j].equals("1")) {
                        PlayerShips++;
                    }
                    if (a[i][j].equals("2")) {
                        ComputerShips++;
                    }
                }
            }
            Display(a);
            Count(a);
        } while ((PlayerShips!=0)&&(ComputerShips!=0));

        if (ComputerShips==0) {
            System.out.println("Hooray! You win the battle :)");
        }
        if (PlayerShips==0) {
            System.out.println("Game Over! The Computer won the battle :(");
        }
    }

    public static void Count(String a[][]) {
        int PlayerShips=0,ComputerShips=0;
        for (int i=0;i<a.length;i++) {
            for (int j=0;j<a[i].length;j++) {
                if (a[i][j].equals("1")) {
                    PlayerShips++;
                }
                if (a[i][j].equals("2")) {
                    ComputerShips++;
                }
            }
        }
        System.out.println("Your ships: " + PlayerShips + " | Computer ships: " + ComputerShips);
    }

    public static void Display (String a[][]) {
        System.out.println("\t   0123456789");
        for (int i=0;i<a.length;i++) {
            System.out.print("\t" + i + " |");
            for (int j=0;j<a[i].length;j++) {
                if (a[i][j].equals("1")) {
                    System.out.print("@");
                } else if ((a[i][j].equals("2"))||(a[i][j].equals("4"))) {
                    System.out.print(" ");
                } else if (a[i][j].equals("3")) {
                    System.out.print("x");
                } else {
                    System.out.print(a[i][j]);
                }
                //System.out.print(a[i][j]);
            }
            System.out.println("| " + i);
        }
        System.out.println("\t   0123456789");
    }
}
