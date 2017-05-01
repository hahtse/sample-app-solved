package de.sample.app.contacts.model;

/**
 * The <code>EMailAdresse</code>.
 ** Used Lombok, this file is delomboked.
 */
public class EMailAdresse
{
    private String address;

    public EMailAdresse()
    {
    }

    public String getAddress()
    {
        return this.address;
    }


    public void setAddress(String address)
    {
        this.address = address;
    }


    public boolean equals(Object o)
    {
        if (o == this)
            return true;
        if (!(o instanceof EMailAdresse))
            return false;
        final EMailAdresse other = (EMailAdresse) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address))
            return false;
        return true;
    }

    public int hashCode()
    {
        final int PRIME = 59;
        int result = 1;
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof EMailAdresse;
    }

    public String toString()
    {
        return "de.sample.app.contacts.model.EMailAdresse(address=" + this.getAddress()+  ")";
    }
}
