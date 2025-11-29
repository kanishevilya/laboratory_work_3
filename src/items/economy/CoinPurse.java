package items.economy;

public class CoinPurse {
    public static final int CopperInSilver = 100;
    public static final int SilverInGold = 10;
    public static final int CopperInGold = 1000;

    protected int gold;
    protected int silver;
    protected int copper;

    public CoinPurse(int gold, int silver, int copper) {
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }

    public int getCopper() {
        return copper;
    }

    public int getTotalInCopper() {
        return copper + silver * CopperInSilver + gold * CopperInGold;
    }

    public void convertValues() {
        this.convertValues(-1);
    }

    public void addCopper(int copper){
        this.copper+=copper;
        convertValues();
    }

    public void convertValues(int defTotal) {
        int total = defTotal > 0 ? defTotal : getTotalInCopper();
        gold = total / CopperInGold;
        total %= CopperInGold;

        silver = total / CopperInSilver;
        copper = total % CopperInSilver;
    }

    private boolean spendValue(int amount, int mult) {
        int total = getTotalInCopper();
        int valInCopper = amount * mult;
        if (total < valInCopper)
            return false;
        total -= valInCopper;
        convertValues(total);
        return true;
    }

    public boolean spendGold(int amount) {
        return spendValue(amount, CopperInGold);
    }

    public boolean spendSilver(int amount) {
        return spendValue(amount, CopperInSilver);
    }

    public boolean spendCopper(int amount) {
        return spendValue(amount, 1);
    }

}
