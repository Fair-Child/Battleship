import java.util.Scanner;

/**
 * This is a maps class.
 */
public class Battleship_map
{
  private Battleship_position [][] usersmaps = new Battleship_position[8][8];
  private Battleship_position [][] computersmaps = new Battleship_position[8][8];
  private int hitGmiss;
  private int usership = 6;
  private int computership = 6;
  Scanner keyin = new Scanner(System.in);

    /**
     * this is a default constructor, it can create two arrays which are belong to user and computer.
     */
  public Battleship_map()
  {
      for (int i = 0; i < 8; i++)
      {
          for (int t = 0; t < 8; t++)
          {
              usersmaps[i][t] = new Battleship_position(Battleship_position.elementtype.nothing, Battleship_position.owner.user);
              computersmaps[i][t] = new Battleship_position(Battleship_position.elementtype.nothing, Battleship_position.owner.computer);
          }
      }
  }

    /**
     * this method let user set their ship's location and grenade's location in user's maps.
     */
  private void setUsersmaps()
  {
      for (int i = 1; i <= 6; i++)
      {
          System.out.print("Enter the coordinates of your ship #" + i + ": ");
          String location = keyin.next();
          int F = location.charAt(0) - 65;
          int S = location.charAt(1) - 49;
          if ((F > 7 || F < 0) || (S > 7 || S < 0))
          {
              i--;
              System.out.println("sorry, coordinates outside the grid. try again.");
          }

          else if (!usersmaps[S][F].empty())
          {
              i--;
              System.out.println("sorry, coordinates already used. try again.");
          }

          else
          {
              usersmaps[S][F].setType(Battleship_position.elementtype.ship);
          }
      }

      for (int i = 1; i <= 4; i++)
      {
          System.out.print("Enter the coordinates of your grenade #" + i + ": ");
          String location = keyin.next();
          int F = location.charAt(0) - 65;
          int S = location.charAt(1) - 49;
          if ((F > 7 || F < 0) || (S > 7 || S < 0))
          {
              i--;
              System.out.println("sorry, coordinates outside the grid. try again.");
          }

          else if (!usersmaps[S][F].empty())
          {
              i--;
              System.out.println("sorry, coordinates already used. try again.");
          }

          else
          {
              usersmaps[S][F].setType(Battleship_position.elementtype.grenade);
          }
      }
  }

    /**
     * this method let computer set its own ship and grenade in computer maps.
     */
  private void setComputersmaps()
  {
      for (int i = 0; i < 6; i++) {
          int x = (int) (Math.random() * 8);
          int y = (int) (Math.random() * 8);

          if (!usersmaps[y][x].empty() || !computersmaps[y][x].empty()) {
              i--;
          }
          else
          {
              computersmaps[y][x].setType(Battleship_position.elementtype.ship);
          }
      }

      for (int t = 0; t < 4; t++) {
          int m = (int) (Math.random() * 8);
          int n = (int) (Math.random() * 8);

          if (!usersmaps[n][m].empty() || !computersmaps[n][m].empty()) {
              t--;
          } else {
              computersmaps[n][m].setType(Battleship_position.elementtype.grenade);
          }
      }
      System.out.println("\nOK, the computer placed its ships and grenades at random. Let's play.\n");
  }

    /**
     * When user attack, You can call this method, it can check the coordinate whether or not available and what on that coordinate.
     * @return if this object belong to user: ship: return "s"; grenade: return "g"; nothing: return "n";
     * if this object belong to computer: ship: return "S"; grenade: return "G"; nothing: return "N";
     */
  private String userattack()
  {
      char S;
      char B;
      int t;
      int m;
      String loc;
      do
      {
          System.out.print("position of your rocket: ");
          loc = keyin.next();
          S = loc.charAt(0);
          t = (int)S;
          t=t-65;
          B = loc.charAt(1);
          m = Character.getNumericValue(B);
          m=m-1;
          if(t>=0&&t<=7&&m>=0&&m<=7)
          {
              if(computersmaps[m][t].getCalled()||usersmaps[m][t].getCalled())
              {
                  System.out.println("position already called.");
              }

              else
              {
                  if (!computersmaps[m][t].hit().equals("N"))
                  {
                     loc = computersmaps[m][t].hit();
                     if (loc.equals("S"))
                     {
                         computership--;
                     }
                      usersmaps[m][t].hit();
                  }

                  else if (!usersmaps[m][t].hit().equals("n"))
                  {
                      loc = usersmaps[m][t].hit();
                      if (loc.equals("s"))
                      {
                          usership--;
                      }
                      computersmaps[m][t].hit();
                  }
                  break;
              }
          }
          else
          {
              System.out.println("Wrong form.");
          }
      }while (t<0||t>7||m<0||m>7||computersmaps[m][t].getCalled()||usersmaps[m][t].getCalled());
      return loc;
  }

    /**
     * When computer attack, You can call this method, it can give a coordinate automatically and what in that coordinate.
     * @return if this object belong to user: ship: return "s"; grenade: return "g"; nothing: return "n";
     * if this object belong to computer: ship: return "S"; grenade: return "G"; nothing: return "N";
     */
  private String computerattack()
  {
      int x = 0;
      int y = 0;
      do
      {
          x = (int)(Math.random()*8);
          y = (int)(Math.random()*8);

      }while(usersmaps[y][x].getCalled());
      String loc1 = usersmaps[y][x].hit();
      String loc2 = computersmaps[y][x].hit();
      x = x + 65;
      y = y + 49;
      char s = (char)x;
      char c = (char)y;
      String t = Character.toString(s);
      String p = Character.toString(c);
      String k ="h" + Character.toString(s)+Character.toString(c);
      System.out.print("position of my rocket: " + t.toUpperCase() + p + "\n");


      if (loc1.equals("s"))
      {
          usership--;
      }
      else if (loc2.equals("S"))
      {
          computership--;
      }

      if (loc1.equals("n"))
      {
          return loc2;
      }

      else
      {
          return loc1;
      }
  }

    /**
     *  You can called this method after every attack, it can show the current maps(the coordinate have been hit or not) to you.
     */
  private void showmaps()
  {
      System.out.println();
      for (int i = 0; i < 8; i++)
      {
          System.out.print("\t");
          for (int t = 0; t < 8; t++)
          {
              if (!usersmaps[i][t].getCalled()&&!computersmaps[i][t].getCalled())
              {
                  System.out.print(usersmaps[i][t] + "  ");
              }

              else if (usersmaps[i][t].getType().equals(Battleship_position.elementtype.nothing))
              {
                  System.out.print(computersmaps[i][t] + "  ");
              }

              else
              {
                  System.out.print(usersmaps[i][t] + "  ");
              }
          }
          System.out.println();
      }
  }

    /**
     * You can call this method, when game end. It will show the all the ship and grenade on maps.
     */
  private void showfinalmaps()
  {
      System.out.println();
      for (int i = 0; i < 8; i++)
      {
          System.out.print("\t");
          for (int t = 0; t < 8; t++)
          {
              if (!usersmaps[i][t].getType().equals(Battleship_position.elementtype.nothing))
              {
                  System.out.print(usersmaps[i][t].showoff() + "  ");
              }

              else
              {
                  System.out.print(computersmaps[i][t].showoff() + "  ");
              }
          }
          System.out.println();
      }
      System.out.println("There are " + hitGmiss + " times missing by hit grenade.");
  }

    /**
     * After ship got hit, you can call this method to check game end or not.
     */
  private void gameend()
  {
      if (usership==0)
      {
          System.out.println("You lost!");
          showfinalmaps();
          System.exit(0);
      }
      else if (computership==0)
      {
          System.out.println("You win!");
          showfinalmaps();
          System.exit(0);
      }
  }

    /**
     * this is run game method.
     */
  public void run()
  {
      setUsersmaps();
      setComputersmaps();

      for (int i = 1; i > 0; i++)
      {
          if (i % 2 != 0)
          {
              String type = userattack();

              if (type.equals("g")||type.equals("G"))
              {
                  System.out.print("boom! grenade.");
                  showmaps();
                  String k = computerattack();
                  hitGmiss++;
                    if (k.equals("g")||k.equals("G"))
                    {
                        hitGmiss++;
                        System.out.print("boom! grenade.");
                        showmaps();
                        i--;
                    }

                    else if (k.equals("s")||k.equals("S"))
                    {
                        System.out.print("ship hit.");
                        gameend();
                        showmaps();
                    }

                    else
                    {
                        System.out.print("nothing.");
                        showmaps();
                    }
              }

              else if (type.equals("s")||type.equals("S"))
              {
                  System.out.print("ship hit.");
                  gameend();
                  showmaps();
              }

              else
              {
                  System.out.print("nothing.");
                  showmaps();
              }
          }

          else
          {
              String t = computerattack();

              if (t.equals("g")||t.equals("G"))
              {
                  System.out.print("boom! grenade.");
                  showmaps();
                  hitGmiss++;
                  String p = userattack();

                  if (p.equals("g")||p.equals("G"))
                  {
                      System.out.print("boom! grenade.");
                      showmaps();
                      hitGmiss++;
                      i--;
                  }
                  else if (p.equals("s")||p.equals("S"))
                  {
                      System.out.print("ship hit.");
                      gameend();
                      showmaps();
                  }

                  else
                  {
                      System.out.print("nothing.");
                      showmaps();
                  }
              }

              else if (t.equals("s")||t.equals("S"))
              {
                  System.out.print("ship hit.");
                  gameend();
                  showmaps();
              }
              else
              {
                  System.out.print("nothing.");
                  showmaps();
              }
          }
      }
  }
}
