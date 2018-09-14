/**
 * Created by user on 1/31/17.
 */
public class TaxModel
{
    private double income;
    private double tax, average, marginal, cpp, ei;

    public static final double BRACKET_0 = 11475.0;
    public static final double BRACKET_1 = 33808.0;
    public static final double BRACKET_2 = 40895.0;
    public static final double BRACKET_3 = 63823.0;
    public static final double RATE_1 = 22.79 / 100.0;
    public static final double RATE_2 = 33.23 / 100.0;
    public static final double RATE_3 = 45.93 / 100.0;
    public static final double RATE_4 = 52.75 / 100.0;

    public static final double EI_RATE = 1.63 / 100.0;
    public static final double EI_MAX = 836.19;

    public static final double CPP_RATE = 4.95 / 100.0;
    public static final double CPP_MAX = 2564.10;
    public static final double CPP_EXEMPT = 3500.0;


    public static void main(String[] args)
    {
        TaxModel model = new TaxModel();
        model.setIncome(52500);
        System.out.println(model.getTax());
        System.out.println(model.getAverageRate());
        System.out.println(model.getMarginalRate());
        System.out.println(model.getCPP());
        System.out.println(model.getEI());
    }

    public void setIncome(double income)
    {
        this.income = income;
    }

    private String convertToString(double i)
    {
        String result = String.format("$%,.2f", i);
        return result;

    }

    public String getTax()
    {
        double mod_income = income;
        if (mod_income - BRACKET_0 > 0)
        {
            mod_income -= BRACKET_0;
            tax = 0;
        } else
        {
            tax = 0;
            marginal = 0;
            return convertToString(tax);
        }

        if (mod_income - BRACKET_1 > 0)
        {
            tax += BRACKET_1*RATE_1;
            mod_income -= BRACKET_1;
        } else
        {
            tax += mod_income*RATE_1;
            marginal = RATE_1;
            return convertToString(tax);
        }

        if (mod_income - BRACKET_2 > 0){
            tax += BRACKET_2*RATE_2;
            mod_income -= BRACKET_2;
        }else {
            tax += mod_income*RATE_2;
            marginal = RATE_2;
            return convertToString(tax);
        }

        if (mod_income - BRACKET_3 > 0){
            tax += BRACKET_3 * RATE_3;
            mod_income -= BRACKET_3;
        }else {
            tax += mod_income*RATE_3;
            marginal = RATE_3;
            return convertToString(tax);
        }

        if (mod_income > 0){
            tax += mod_income * RATE_4;
            marginal = RATE_4;

        }
        return convertToString(tax);

    }

    public String getAverageRate(){
        average = Math.round((tax/income)*100);
        return String.format("%d%%", (int)average);
    }

    public String getMarginalRate(){
        marginal *= 100;
        return String.format("%d%%", (int)marginal);
    }

    public String getCPP(){
        double mod_income;
        if (income - CPP_EXEMPT > 0)
        {
            mod_income = income - CPP_EXEMPT;
            cpp = mod_income * CPP_RATE;
        } else
        {
            cpp = 0;
        }

        if (cpp > CPP_MAX){
            cpp = CPP_MAX;
        }
        return convertToString(cpp);
    }

    public String getEI(){
        if (income * EI_RATE > EI_MAX){
            ei = EI_MAX;
        }else{
            ei = income * EI_RATE;
        }
        return convertToString(ei);
    }
}
