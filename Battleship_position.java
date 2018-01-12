/**
 * This is a element class.
 */
public class Battleship_position
{
    /**
     * creat two enum type: elementtype and owner
     * elsementtype: represent object type(ship, grenade, nothing)
     * owner: represent who own this object(user, computer)
     */
    public enum elementtype{ship,grenade,nothing}
    public enum owner{user, computer}
    private elementtype type;
    private owner who;
    private boolean called;
    private String stringtype = "_";


    /**
     * this is constructor of Battleship_position class
     * include two arguments.
     * @param type elementtype(ship, grenade, nothing)
     * @param who owner(user, computer)
     */
    public Battleship_position(elementtype type, owner who)
    {
        if (type.equals(elementtype.valueOf("ship")))
        {
            this.type = type;
        }

        else if (type.equals(elementtype.valueOf("grenade")))
        {
            this.type = type;
        }

        else
        {
            this.type = type;
        }

        this.who = who;
    }

    /**
     * this is a accessor of type
     * @return elementtype: ship if this object is ship or grenade if this object is grenade or nothing if this object is empty.
     */
    public elementtype getType()
    {
        return type;
    }

    /**
     * this is a accessor of who.
     * @return owner type: user if this object belong to user or computer if this object belong to computer
     */
    public owner getWho()
    {
        return who;
    }

    /**
     * this is a accessor of getcalled
     * @return true if this object have been called.
     */
    public boolean getCalled()
    {
        return called;
    }

    /**
     * this is a mutator of type.
     * @param type you can keyin a elementtype data to change type of this object.
     */
    public void setType(elementtype type)
    {
        this.type = type;
    }

    /**
     * When this object was hit, You can call this method to chang check what is the type of this object.
     * @return if this object belong to user: ship: return "s"; grenade: return "g"; nothing: return "n";
     * if this object belong to computer: ship: return "S"; grenade: return "G"; nothing: return "N";
     */
    public String hit()
    {
        called = true;
        if (who.equals(owner.user))
        {
            if (type.equals(elementtype.ship))
            {
                stringtype = "s";
                return "s";
            }

            else if (type.equals(elementtype.grenade))
            {
                stringtype = "g";
                return "g";
            }

            else
            {
                stringtype = "*";
                return "n";
            }
        }

        else
        {
            if (type.equals(elementtype.ship))
            {
                stringtype = "S";
                return "S";
            }

            else if (type.equals(elementtype.grenade))
            {
                stringtype = "G";
                return "G";
            }

            else
            {
                stringtype = "*";
                return "N";
            }
        }
    }

    /**
     * You can use this method let object show off what type it is, when game end.
     * @return if this object belong to user: ship: return "s"; grenade: return "g"; nothing: return "n";
     * if this object belong to computer: ship: return "S"; grenade: return "G"; nothing: return "N";
     */
    public String showoff()
    {
        if (who.equals(owner.user))
        {
            if (type == elementtype.ship)
                return "s";

            else if (type.equals(elementtype.grenade))
                return "g";

            else
            {
                return "_";
            }
        }

        else
        {
            if (type == elementtype.ship)
                return "S";

            else if (type.equals(elementtype.grenade))
                return "G";

            else
                return "_";
        }
    }

    /**
     * this is toString method.
     * @return stringtype.
     */
    public String toString()
    {
      return stringtype;
    }

    /**
     * You can use this method check the type of this object whether nothing.
     * @return if type is nothing then return true, if not return false.
     */
    public boolean empty()
    {
        return type.equals(elementtype.nothing);
    }
}
