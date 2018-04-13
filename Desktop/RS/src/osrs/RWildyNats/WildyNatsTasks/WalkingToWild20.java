package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import static osrs.RWildyNats.WildyNats.*;

public class WalkingToWild20 extends Task {
    private final Walker walker = new Walker(ctx);
   // public static final Tile[] pathToWildylevel20V2 = {new Tile(2949, 3821, 0), new Tile(2952, 3821, 0), new Tile(2955, 3821, 0), new Tile(2958, 3821, 0), new Tile(2961, 3821, 0), new Tile(2964, 3818, 0), new Tile(2967, 3815, 0), new Tile(2970, 3812, 0), new Tile(2973, 3811, 0), new Tile(2976, 3809, 0), new Tile(2978, 3806, 0), new Tile(2979, 3803, 0), new Tile(2979, 3800, 0), new Tile(2981, 3797, 0), new Tile(2984, 3794, 0), new Tile(2986, 3791, 0), new Tile(2986, 3788, 0), new Tile(2988, 3785, 0), new Tile(2988, 3782, 0), new Tile(2991, 3780, 0), new Tile(2994, 3779, 0), new Tile(2997, 3776, 0), new Tile(2998, 3773, 0), new Tile(3000, 3770, 0), new Tile(3000, 3767, 0), new Tile(3001, 3764, 0), new Tile(3002, 3761, 0), new Tile(3001, 3758, 0), new Tile(3000, 3755, 0), new Tile(3000, 3752, 0), new Tile(3001, 3749, 0), new Tile(3001, 3746, 0), new Tile(3001, 3743, 0), new Tile(3001, 3740, 0), new Tile(3001, 3737, 0), new Tile(3001, 3734, 0), new Tile(3001, 3731, 0), new Tile(3001, 3728, 0), new Tile(3001, 3725, 0), new Tile(3000, 3722, 0), new Tile(3000, 3719, 0), new Tile(2997, 3718, 0), new Tile(2994, 3716, 0), new Tile(2991, 3716, 0), new Tile(2988, 3716, 0), new Tile(2985, 3716, 0), new Tile(2982, 3716, 0), new Tile(2979, 3716, 0), new Tile(2976, 3715, 0), new Tile(2973, 3715, 0), new Tile(2969, 3712, 0), new Tile(2966, 3711, 0), new Tile(2963, 3711, 0), new Tile(2960, 3711, 0), new Tile(2957, 3711, 0), new Tile(2954, 3709, 0), new Tile(2952, 3705, 0), new Tile(2952, 3702, 0), new Tile(2952, 3699, 0), new Tile(2952, 3696, 0), new Tile(2952, 3693, 0), new Tile(2952, 3690, 0), new Tile(2952, 3687, 0), new Tile(2952, 3683, 0), new Tile(2954, 3680, 0), new Tile(2954, 3677, 0), new Tile(2954, 3674, 0), new Tile(2953, 3671, 0), new Tile(2951, 3668, 0), new Tile(2951, 3665, 0)};
    public static final Tile[] pathToWildylevel20 = {new Tile(3303, 3860, 0), new Tile(3301, 3856, 0), new Tile(3301, 3852, 0), new Tile(3301, 3848, 0), new Tile(3301, 3844, 0), new Tile(3301, 3840, 0), new Tile(3301, 3836, 0), new Tile(3301, 3832, 0), new Tile(3301, 3828, 0), new Tile(3301, 3824, 0), new Tile(3301, 3820, 0), new Tile(3300, 3816, 0), new Tile(3299, 3812, 0), new Tile(3299, 3808, 0), new Tile(3299, 3804, 0), new Tile(3300, 3800, 0), new Tile(3300, 3796, 0), new Tile(3300, 3792, 0), new Tile(3300, 3788, 0), new Tile(3300, 3784, 0), new Tile(3299, 3780, 0), new Tile(3298, 3776, 0), new Tile(3295, 3773, 0), new Tile(3295, 3769, 0), new Tile(3295, 3765, 0), new Tile(3295, 3761, 0), new Tile(3295, 3757, 0), new Tile(3294, 3753, 0), new Tile(3293, 3749, 0), new Tile(3293, 3745, 0), new Tile(3294, 3741, 0), new Tile(3295, 3737, 0), new Tile(3295, 3733, 0), new Tile(3295, 3729, 0), new Tile(3295, 3725, 0), new Tile(3295, 3721, 0), new Tile(3295, 3717, 0), new Tile(3295, 3713, 0), new Tile(3295, 3709, 0), new Tile(3295, 3705, 0), new Tile(3295, 3701, 0), new Tile(3295, 3697, 0), new Tile(3295, 3693, 0), new Tile(3295, 3689, 0), new Tile(3295, 3685, 0), new Tile(3294, 3681, 0), new Tile(3294, 3677, 0)};
    int random = Random.nextInt(0,2);
    public WalkingToWild20(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

        return   pked || ctx.players.local().healthPercent()<60
                && pathToWildylevel20[pathToWildylevel20.length-1].distanceTo(ctx.players.local())>3
                && ctx.inventory.select().count() > 6
                || ctx.inventory.select().id(563).count(true) == 1;
    }

    @Override
    public void execute() {
        CurrentTask = "Walking To 20Wild";
        ctx.input.speed(Random.nextInt(60,75));

if(!ctx.players.local().inMotion() && new Tile(3303,3860,0).distanceTo(ctx.players.local())<2) {
   ctx.movement.step(new Tile(ctx.players.local().tile().x()+Random.nextInt(1,2),ctx.players.local().tile().y()+Random.nextInt(1,2),0));
}
        int random2 = org.powerbot.script.Random.nextInt(5,14);
        if(random2==8) {
            ctx.camera.pitch(org.powerbot.script.Random.nextInt(50, 99));
            ctx.camera.angle(org.powerbot.script.Random.nextInt(0, 360));

        }
        if(!ctx.movement.running() && ctx.movement.energyLevel() > org.powerbot.script.Random.nextInt(20,60)) {
            ctx.widgets.component(160,27).click();
        }

if(random ==1) {
    if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 9) {
        if (pked || ctx.players.local().inCombat()|| ctx.inventory.select().id(LAWRUNE_ID).count(true) <2) {

            walker.walkPath(pathToWildylevel20);


        }
    }
} else {

    if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 9) {
        if (pked || ctx.players.local().inCombat()|| ctx.inventory.select().id(LAWRUNE_ID).count(true) <2) {

            walker.walkPath(pathToWildylevel20);


        }
    }
}

        if(ctx.combat.health() < 1){
            died++;
        }
    }
}
