package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

import static osrs.RWildyNats.WildyNats.CurrentTask;

public class EquippingAndTP extends Task {


    public EquippingAndTP(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

        return ctx.equipment.select().id(1381).count() > 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 4 && ctx.inventory.select().count() == 3&& !ctx.bank.opened() ||
                ctx.equipment.id(1381).count() < 1 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6 && ctx.inventory.select().count() == 3&& !ctx.bank.opened();
    }

    @Override
    public void execute() {
        CurrentTask = "Equipping";
        final Item airstaff = ctx.inventory.select().id(1381).poll();

        ctx.game.tab(Game.Tab.INVENTORY);
        if ((ctx.equipment.select().id(1381).count() < 1 && !ctx.bank.opened())) {

            if (airstaff.valid()) {
                airstaff.click();
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.equipment.select().id(1381).count() == 1;
                    }
                });
            }


           /* for (Item i : ctx.inventory.select().id(burningamulet)) {
                i.click();
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.equipment.select().id(burningamulet).count() == 1;
                    }
                });
                break;
            }*/

        } else {
            ctx.bank.close();
        }


       /* ctx.game.tab(Game.Tab.EQUIPMENT);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.game.tab(Game.Tab.EQUIPMENT);
            }
        });
        ctx.equipment.itemAt(Equipment.Slot.NECK).interact("Lava Maze");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.chat.isEmpty();
            }
        });
        //ctx.input.click(Random.nextInt(220,280), 402, true);

        ctx.chat.continueChat("1");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return NatureToBank[0].distanceTo(ctx.players.local()) < 10;
            }
        });*/
        ctx.game.tab(Game.Tab.INVENTORY);


    }
}


