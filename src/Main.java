import characters.hobbit.Hobbit;
import characters.human.Human;
import characters.human.Knight;
import characters.wizard.Wizard;
import enums.*;
import exceptions.AccessDeniedException;
import exceptions.InventoryFullException;
import global.Event;
import global.EventsManager;
import global.ManaSource;
import items.documents.Letter;
import items.economy.CoinPurse;
import items.magical.MagicalRing;
import items.magical.Spell;
import items.magical.Staff;
import items.weapon.Armor;
import items.weapon.Sword;
import locations.Location;
import locations.districts.City;
import locations.districts.Country;
import locations.fortress.Gate;
import locations.fortress.Isengard;
import locations.fortress.OrthancTower;

import java.util.ArrayList;
import java.util.List;

import buildings.shop.Shop;
import buildings.tavern.Tavern;

public class Main {
    public static void main(String[] args) {
        City bree = new City("Брыл", "Небольшой торговый город.", null, null);
        Tavern tavern = new Tavern("Таверна в Брыле", bree, 10, 10, 10, null);
        Shop shop = new Shop("Лавка в Брыле", bree);

        bree.addBuilding(tavern);
        bree.addBuilding(shop);

        OrthancTower orthanc = new OrthancTower("Ортханк", "Высокая башня, построенная нуменорцами.", LocationType.Fortress, null);

        Gate isengardGate = new Gate("Врата Изендгарда", "Единственный вход в Изендгард.", null);

        Isengard isengard = new Isengard("Айзенгард", "Каменное плато, окружённое горами.", null, orthanc, isengardGate);

        orthanc.setParentLocation(isengard);
        isengardGate.setParentLocation(isengard);
        orthanc.addNeighboringLocation(isengardGate);
        bree.addNeighboringLocation(isengardGate);

        ManaSource.getInstance().setManaBonus(orthanc, 500);

        CoinPurse gandalfPurse = new CoinPurse(1, 10, 50);
        Staff gandalfStaff;
        try {
            gandalfStaff = new Staff("Посох Гэндальфа", "Старый деревянный посох", null, 100, 50, Effect.Protection, Material.Wood);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        Wizard gandalf = new Wizard("Гэндальф Серый", 100, bree, gandalfPurse, "Серый", gandalfStaff, 10, 100, 50, 100, 100);
        bree.addCharacter(gandalf);

        CoinPurse innkeeperPurse = new CoinPurse(10, 50, 200);
        Human innkeeper = new Human("Барлиман Баттербур", 50, bree, innkeeperPurse, 5, 10, 5);
        bree.addCharacter(innkeeper);
        tavern.changeOwner(innkeeper);

        CoinPurse sarumanPurse = new CoinPurse(1, 500, 1000);
        Staff sarumanStaff;
        try {
            sarumanStaff = new Staff("Посох Сарумана", "Белый посох", null, 200, 80, Effect.Corruption, Material.Metal);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        Wizard saruman = new Wizard("Саруман Белый", 150, orthanc, sarumanPurse, "Белый", sarumanStaff, 20, 200, 80, 200, 200);
        orthanc.addCharacter(saruman);
        orthanc.addWizard(saruman);

        MagicalRing ring;
        try {
            ring = new MagicalRing("Кольцо силы", "Малое кольцо власти", saruman, 1000, 5, Material.MagicMetal, 10, false);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (!saruman.isEquip(ring)) {
            ring.equip();
        }

        Armor armorPrototype;
        try {
            armorPrototype = new Armor("Броня", "Броня", null, 100, 50, 40, Material.Metal);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        Sword swordPrototype;
        try {
            swordPrototype = new Sword("Меч", "Меч", null, 100, 50, 40, Material.Metal);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        CoinPurse guardPurse = new CoinPurse(1, 1, 10);
        Knight guard1;
        try {
            guard1 = new Knight("Орк страж 1", 80, isengardGate, guardPurse, 20, 50, 20, 2, 100, armorPrototype, swordPrototype, isengard);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        isengardGate.addCharacter(guard1);
        isengardGate.addGuard(guard1);

        gandalf.setFatigue(90);

        CoinPurse frodoPurse = new CoinPurse(0, 0, 10);
        Hobbit frodo = new Hobbit("Фродо", 100, null, frodoPurse, 100, 70, 20);

        Letter letter;
        try {
            letter = new Letter("Письмо Фродо", "Срочное сообщение", 0, "Беги!", "Уходи из Шира!", gandalf, frodo);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            gandalf.addItemToBag(letter);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
        }
        gandalf.removeItemFromBag(letter);
        try {
            letter.entrustTo(innkeeper);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
        }

        gandalf.moveTo(isengardGate);

        if (isengardGate.isLocked()) {
            isengardGate.open(guard1);
        }

        isengardGate.close();
        gandalf.setFear(new Event(List.of(gandalf), isengardGate, Reason.SawSomething, "Необъяснимый страх"));

        gandalf.moveTo(orthanc);

        Location firstFloor = new Location("Первый этаж", "Первый этаж Ортханка.", LocationType.Castle, orthanc, new ArrayList<>(List.of(saruman)), new ArrayList<>(List.of(orthanc))) {
        };

        gandalf.moveTo(firstFloor);

        Location secondFloor = new Location("Второй этаж", "Второй этаж Ортханка.", LocationType.Castle, orthanc, new ArrayList<>(), new ArrayList<>(List.of(firstFloor))) {
        };

        firstFloor.addNeighboringLocation(secondFloor);
        gandalf.moveTo(secondFloor);
        saruman.moveTo(secondFloor);

        saruman.speak(gandalf, "Вот ты и пришёл, Гэндальф.");
        gandalf.viewReceivedMessages();

        gandalf.speak(saruman, "Да, пришёл. Пришёл за обещанной помощью, Саруман Белый.");
        saruman.viewReceivedMessages();

        saruman.setRage(new Event(List.of(saruman), orthanc, Reason.HeardSomething, "Ярость из-за обращения к Саруману"));

        saruman.speak(gandalf, "Неужто за помощью, Гэндальф Серый? Кто бы мог подумать!");
        gandalf.viewReceivedMessages();
        ring.unEquip();

        EventsManager.getInstance().executeEvents();

        demo();
    }

    private static void demo() {
        System.out.println("Демо остальных функций");
        Country gondor = new Country("Гондор", "Королевство людей", null, null);
        City minas = new City("Минас Тирит", "Столица Гондора", gondor, gondor);

        Shop shop = new Shop("Лавка оружейника", minas);
        shop.setOpen(true);

        Human greedyMerchant = new Human("Жадный торговец", 100, minas, new CoinPurse(10, 10, 10), 50, 80, 50);
        Armor goldenArmor;
        try {
            goldenArmor = new Armor("Золотой доспех", "Блестящий", null, 1000, 30, 15, Material.Gold);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        shop.addItem(goldenArmor, 500);

        greedyMerchant.getCoinPurse().addCopper(600);
        try {
            shop.purchaseItem(greedyMerchant, goldenArmor);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        shop.sellItem(greedyMerchant, goldenArmor);

        Knight aragorn;
        try {
            aragorn = new Knight("Арагорн", 120, minas, true, new ArrayList<>(), false, 0, new CoinPurse(50, 50, 50), 90, 50, 70, 80, 100, null, null, gondor);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        Knight orc;
        try {
            orc = new Knight("Орк", 100, minas, true, new ArrayList<>(), false, 0, new CoinPurse(0, 0, 0), 80, 0, 40, 50, 100, null, null, null);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        orc.setRage(new Event(List.of(orc), minas, Reason.SawSomething, "Увидел врага"));
        System.out.println("Орк в ярости: " + orc.isRage());

        int hpBefore = aragorn.getHealth();
        orc.attack(aragorn);
        System.out.println("Урон от ярости: " + (hpBefore - aragorn.getHealth()));

        aragorn.setFear(new Event(List.of(aragorn), minas, Reason.SawSomething, "Испуг"));
        aragorn.attack(orc);

        Hobbit frodo = new Hobbit("Фродо", 80, minas, new CoinPurse(5, 5, 5), 60, 30, 20);
        Sword sword;
        try {
            sword = new Sword("Меч", "Меч", null, 200, 80, 30, Material.MagicMetal);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            aragorn.addItemToBag(sword);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }

        Hobbit sam = new Hobbit("Сэм", 85, minas, new CoinPurse(3, 3, 3), 50, 25, 5);
        System.out.println("Голод: " + sam.getThirstForFood());

        City bree = new City("Бри", "Город", gondor, gondor);
        minas.addNeighboringLocation(bree);
        sam.moveTo(bree);
        System.out.println("После пути: " + sam.getThirstForFood());

        Tavern tavern = new Tavern("Таверна", bree, 5, 50, 20, greedyMerchant);
        tavern.setOpen(true);
        tavern.serveMeal(sam);

        Country mordor = new Country("Мордор", "Темная страна", null, null);
        Gate blackGate = new Gate("Черные врата", "Врата Мордора", mordor);

        try {
            blackGate.checkAccess(aragorn);
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
            return;
        }

        Staff staff;
        try {
            staff = new Staff("Посох", "Магический", null, 1000, 100, Effect.Protection, Material.MagicWood);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        Wizard gandalf = new Wizard("Гэндальф", 150, minas, new CoinPurse(100, 100, 100), "Белый", staff, 95, 200, 80, 500, 500);

        Spell fireball = new Spell("Огонь", 50, SpellType.Attack, 40);
        gandalf.castSpell(fireball, orc);

        Letter letter;
        try {
            letter = new Letter("Письмо", "Запечатано", 10, "Важное", "Текст", aragorn, gandalf);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            letter.entrustTo(frodo);
        } catch (InventoryFullException e) {
            System.out.println(e.getMessage());
            return;
        }
        frodo.moveTo(minas);

        EventsManager.getInstance().executeEvents();
    }
}