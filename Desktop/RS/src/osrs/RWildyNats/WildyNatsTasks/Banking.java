package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

import static osrs.RWildyNats.WildyNatsTasks.WalkingToBank.VarTPtoBankPath;
import static osrs.RWildyNats.WildyNats.*;


public class Banking extends Task {
    public boolean equipment = false;
    public static boolean bank2 = false;
    public Banking(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        equipment = false;

        return ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 4
                && ctx.inventory.select().id(LAWRUNE_ID).count(true) != 28
                && VarTPtoBankPath[VarTPtoBankPath.length-1].distanceTo(ctx.players.local())<3;

    }

    @Override
    public void execute() {



        CurrentTask = "Banking Varrock";

        if (ctx.bank.opened()) {
            int randomizer = Random.nextInt(0, 3);

            final Item airstaff = ctx.bank.select().id(1381).poll();
            final Item lawrunes = ctx.bank.select().id(LAWRUNE_ID).poll();
            final Item firerune = ctx.bank.select().id(FIRERUNE_ID).poll();

            if (ctx.bank.depositInventory()) {
                final int inventcount = ctx.inventory.select().count();
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.inventory.select().count() != inventcount;
                    }

                }, 100, 20);

            }
            if (!lawrunes.valid() || !airstaff.valid() && ctx.equipment.select().id(1381).count() < 1 && ctx.inventory.select().id(1381).count() < 1) {
                ctx.controller.stop();
            }
            if (ctx.equipment.select().id(1381).count() < 1) {
                if (ctx.bank.depositEquipment()) {
                    final int equipcount = ctx.equipment.select().count();
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.equipment.select().count() != equipcount;
                        }

                    }, 100, 20);

                }
            }


            if (ctx.inventory.select().id(LAWRUNE_ID).count() == 0 && ctx.bank.opened()) {

                if (randomizer == 1) {
                    ctx.bank.withdraw(FIRERUNE_ID, 1);
                    Condition.sleep(Random.getDelay());

                    ctx.bank.withdraw(LAWRUNE_ID, 28);
                    Condition.sleep(Random.getDelay());

                    Condition.sleep(Random.getDelay());
                    if (ctx.equipment.select().id(1381).count() < 1) {
                        ctx.bank.withdraw(1381, 1);
                        Condition.sleep(Random.getDelay());
                    }


                } else {
                    if (ctx.equipment.select().id(1381).count() < 1) {
                        ctx.bank.withdraw(1381, 1);
                        Condition.sleep(Random.getDelay());
                    }
                    ctx.bank.withdraw(LAWRUNE_ID, 28);
                    Condition.sleep(Random.getDelay());

                    ctx.bank.withdraw(FIRERUNE_ID, 1);
                    Condition.sleep(Random.getDelay());
                }

                ctx.bank.close();
                Condition.sleep(Random.getDelay());


            }
        }
            else {
            if (ctx.bank.inViewport()) {
                ctx.movement.step(ctx.bank.nearest());
                ctx.bank.open();
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.bank.opened();
                    }
                }, 250, 20);

            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
            if(ctx.chat.chatting()) {
                ctx.bank.open();
            }
        }



    }
}





