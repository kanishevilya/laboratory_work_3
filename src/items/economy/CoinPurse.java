package items.economy;

import java.util.Objects;

public class CoinPurse {
    public static final int CopperInSilver = 100;
    public static final int CopperInGold = 1000;

    protected int gold;
    protected int silver;
    protected int copper;

    public CoinPurse(int gold, int silver, int copper) {
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
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

    private boolean spendValue(int amount) {
        int total = getTotalInCopper();
        if (total < amount)
            return false;
        total -= amount;
        convertValues(total);
        return true;
    }

    public boolean spendCopper(int amount) {
        return spendValue(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CoinPurse coinPurse)) return false;
        return gold == coinPurse.gold && silver == coinPurse.silver && copper == coinPurse.copper;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gold, silver, copper);
    }
}
