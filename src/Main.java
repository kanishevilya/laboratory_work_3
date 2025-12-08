import buildings.training.TrainingCamp;
import characters.hobbit.Hobbit;
import characters.human.Human;
import characters.human.Knight;
import characters.wizard.Wizard;
import enums.*;
import global.Event;
import global.EventsManager;
import global.ManaSourse;
import items.documents.Letter;
import items.economy.CoinPurse;
import items.magical.MagicalRing;
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

        OrthancTower orthanc = new OrthancTower("Ортханк",
                "Высокая башня, построенная нуменорцами.",
                LocationType.Fortress,
                null);

        Gate isengardGate = new Gate("Врата Изендгарда", "Единственный вход в Изендгард.", null);

        Isengard isengard = new Isengard(
                "Айзенгард",
                "Каменное плато, окружённое горами.",
                null,
                orthanc,
                isengardGate
        );

        orthanc.setParentLocation(isengard);
        isengardGate.setParentLocation(isengard);
        orthanc.addNeighboringLocation(isengardGate);
        orthanc.addNeighboringLocation(isengardGate);
        bree.addNeighboringLocation(isengardGate);

        ManaSourse.getInstance().setManaBonus(orthanc, 500);

        CoinPurse gandalfPurse = new CoinPurse(1, 10, 50);
        Staff gandalfStaff = new Staff("Посох Гэндальфа", "Старый деревянный посох",
                null, 100, 50, Effect.Protection, Material.Wood);

        Wizard gandalf = new Wizard("Гэндальф Серый", 100, bree, gandalfPurse, "Серый",
                gandalfStaff, 10, 100, 50, 100, 100);
        bree.addCharacter(gandalf);

        CoinPurse innkeeperPurse = new CoinPurse(10, 50, 200);
        Human innkeeper = new Human("Барлиман Баттербур", 50, bree, innkeeperPurse,
                5, 10, 5);
        bree.addCharacter(innkeeper);
        tavern.changeOwner(innkeeper);

        CoinPurse sarumanPurse = new CoinPurse(1, 500, 1000);
        Staff sarumanStaff = new Staff("Посох Сарумана", "Белый посох",
                null, 200, 80, Effect.Corruption, Material.Metal);

        Wizard saruman = new Wizard("Саруман Белый", 150, orthanc, sarumanPurse,
                "Белый", sarumanStaff, 20, 200, 80, 200, 200);
        orthanc.addCharacter(saruman);
        orthanc.addWizard(saruman);

        MagicalRing ring = new MagicalRing("Кольцо силы", "Малое кольцо власти",
                saruman, 1000, 5, Effect.Corruption, Material.MagicMetal, 10, false);
        ring.equip();

        Armor armorPrototype = new Armor("Броня", "Броня", null, 100, 50, 40, Material.Metal);
        Sword swordPrototype = new Sword("Меч", "Меч", null, 100, 50, 40, Material.Metal);

        CoinPurse guardPurse = new CoinPurse(1, 1, 10);
        Knight guard1 = new Knight("Орк страж 1", 80, isengardGate, guardPurse, 20,
                50, 20, 2, 100, armorPrototype, swordPrototype, isengard);
        isengardGate.addCharacter(guard1);
        isengardGate.addGuard(guard1);

        gandalf.setFatigue(90);

        CoinPurse frodoPurse = new CoinPurse(0,0,10);
        Hobbit frodo = new Hobbit("Фродо", 100, null, frodoPurse, 100, 70, 20 );

        Letter letter = new Letter("Письмо Фродо", "Срочное сообщение",
                0, "Беги!", "Уходи из Шира!", gandalf, frodo);
        gandalf.addItemToBag(letter);
        gandalf.removeItemFromBag(letter);
        letter.entrustTo(innkeeper);

        gandalf.moveTo(isengardGate);

        if (isengardGate.isLocked()) {
            isengardGate.open(guard1);
        }

        isengardGate.close();
        gandalf.setFear(new Event(List.of(gandalf), isengardGate,
                Reason.SawSomething, "Необъяснимый страх"));

        gandalf.moveTo(orthanc);

        Location firstFloor = new Location("Первый этаж", "Первый этаж Ортханка.",
                LocationType.Castle, orthanc,
                new ArrayList<>(List.of(saruman)),
                new ArrayList<>(List.of(orthanc))) {};

        gandalf.moveTo(firstFloor);

        Location secondFloor = new Location("Второй этаж", "Второй этаж Ортханка.",
                LocationType.Castle, orthanc,
                new ArrayList<>(),
                new ArrayList<>(List.of(firstFloor))) {};

        firstFloor.addNeighboringLocation(secondFloor);
        gandalf.moveTo(secondFloor);
        saruman.moveTo(secondFloor);

        saruman.speak(gandalf, "Вот ты и пришёл, Гэндальф.");
        gandalf.viewReceivedMessages();

        gandalf.speak(saruman, "Да, пришёл. Пришёл за обещанной помощью, Саруман Белый.");
        saruman.viewReceivedMessages();

        saruman.setRage(new Event(List.of(saruman),
                orthanc, Reason.HeardSomething, "Ярость из-за обращения к Саруману"));

        saruman.speak(gandalf, "Неужто за помощью, Гэндальф Серый? Кто бы мог подумать!");
        gandalf.viewReceivedMessages();


        ExampleOfShop();
        ExampleOfTraining();
        ExampleOfTavern();
        EventsManager.getInstance().executeEvents();
    }

    public static void ExampleOfShop(){
        Country gondor = new Country("Gondor", "Humans Country", null, null);
        City mainCity = new City("MainCity", "MainCity of Goundor", gondor, gondor);
        mainCity.openGates();
        gondor.addCity(mainCity);

        Human human= new Human("Ilya", 100, mainCity, new CoinPurse(0,0,100), 100,100,100);

        Shop shop = new Shop("ShopInShir", mainCity);
        human.observe(shop);
        shop.setOpen(true);
        Armor armor = new Armor("Gold armor", "Armor from gold",null, 10000, 100, 20, Material.Gold);
        armor.setValue(5000);
        shop.addItem(armor, armor.getValue());

        shop.purchaseItem(human, armor);
        shop.sellItem(human, armor);
        shop.setPrice(armor, shop.getPrice(armor)*2);
    }

    public static void ExampleOfTraining(){
        Country rohan = new Country("Rohan", "Rohan country", null, null);
        City rohanCity = new City("Rohans Main city", "City from Rohan", rohan, rohan);
        rohan.addCity(rohanCity);
        TrainingCamp camp = new TrainingCamp("Camp", BuildingType.TrainingCamp, rohanCity, false, 0);
        camp.changeTrainingCost(1000);
        Knight knight = new Knight("Ilya", 100, rohanCity, new CoinPurse(100,100,100), 100, 10, 100, 10,100, rohan);
        knight.setArmor(new Armor("Steel Armor", "Armor from Steel", knight,  300, 100, 20, Material.Metal));
        knight.setSword(new Sword("Steel Sword", "Sword from Steel", knight,  100, 100, 5, Material.Metal));

        knight.learnFencing();
    }

    public static void ExampleOfTavern(){
        Country rivendell = new Country("Rivendell", "Rivendell country", null, null);
        City rivendellCity = new City("Rivendell Main city", "City from Rivendell", rivendell, rivendell);
        rivendell.addCity(rivendellCity);
        Human owner= new Human("Owner Ilya", 100, rivendellCity, new CoinPurse(0,0,100), 100,100,100);
        Human human= new Human("Aragorn", 100, rivendellCity, new CoinPurse(0,4,100), 100,100,100);

        Tavern tavern = new Tavern("Best Tavern", rivendellCity, 10, 100,10, owner);
        tavern.rentRoom(human, 1);
        tavern.serveMeal(human);
    }

    public static void ExampleOfWizard(){
        Country rivendell = new Country("Rivendell", "Rivendell country", null, null);
        City rivendellCity = new City("Rivendell Main city", "City from Rivendell", rivendell, rivendell);
        rivendell.addCity(rivendellCity);

        Staff gandalfStaff = new Staff("Посох Гэндальфа", "Старый деревянный посох",
                null, 100, 50, Effect.Protection, Material.MagicWood);

        Wizard gandalf = new Wizard("Гэндальф Серый", 100, rivendell, new CoinPurse(100,100,100), "Серый",
                gandalfStaff, 10, 100, 50, 100, 100);


    }
}
