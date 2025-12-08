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

import locations.fortress.Gate;
import locations.fortress.Isengard;
import locations.fortress.OrthancTower;

import java.util.ArrayList;
import java.util.List;

import buildings.shop.Shop;
import buildings.tavern.Tavern;

public class Main {
    public static void main(String[] args) {
        City bree = new City("Брыл", "Небольшой торговый город.", null);
        Tavern tavern = new Tavern("Таверна в Брыле", bree, 10, 10, 10, null);
        Shop shop = new Shop("Лавка в Брыле", bree);

        bree.addBuilding(tavern);
        bree.addBuilding(shop);

        OrthancTower orthanc = new OrthancTower("Ортханк",
                "Высокая башня, построенная нуменорцами.",
                LocationType.Fortress,
                null, 100);

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
        Knight guard1 = new Knight("Орк-страж 1", 80, isengardGate, guardPurse, 20,
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
        gandalf.setFear(new Event("Необъяснимый страх", isengardGate,
                Reason.SawSomething, List.of(gandalf)));

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

        saruman.setRage(new Event("Ярость из-за обращения к Саруману",
                orthanc, Reason.HeardSomething, List.of(saruman)));

        saruman.speak(gandalf, "Неужто за помощью, Гэндальф Серый? Кто бы мог подумать!");
        gandalf.viewReceivedMessages();

        EventsManager.getInstance().executeEvents();
    }
}
