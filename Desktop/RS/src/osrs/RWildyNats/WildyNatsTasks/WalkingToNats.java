package osrs.RWildyNats.WildyNatsTasks;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import static osrs.RWildyNats.WildyNats.*;

/**
 * Created by Janciiiii on 3.4.2018.
 */
public class WalkingToNats extends Task {
    public static Tile[] BankToWild2 = {new Tile(3243, 3523, 0), new Tile(3243, 3526, 0), new Tile(3244, 3529, 0), new Tile(3245, 3532, 0), new Tile(3247, 3535, 0), new Tile(3247, 3538, 0), new Tile(3250, 3538, 0), new Tile(3253, 3541, 0), new Tile(3256, 3544, 0), new Tile(3259, 3546, 0), new Tile(3262, 3547, 0), new Tile(3263, 3550, 0), new Tile(3263, 3553, 0), new Tile(3263, 3556, 0), new Tile(3263, 3559, 0), new Tile(3263, 3562, 0), new Tile(3263, 3565, 0), new Tile(3263, 3568, 0), new Tile(3263, 3571, 0), new Tile(3263, 3574, 0), new Tile(3263, 3577, 0), new Tile(3265, 3580, 0), new Tile(3265, 3583, 0), new Tile(3265, 3586, 0), new Tile(3265, 3589, 0), new Tile(3266, 3592, 0), new Tile(3266, 3595, 0), new Tile(3266, 3598, 0), new Tile(3265, 3601, 0), new Tile(3265, 3604, 0), new Tile(3265, 3607, 0), new Tile(3265, 3610, 0), new Tile(3268, 3612, 0), new Tile(3271, 3615, 0), new Tile(3274, 3618, 0), new Tile(3277, 3621, 0), new Tile(3280, 3624, 0), new Tile(3282, 3627, 0), new Tile(3284, 3630, 0), new Tile(3284, 3633, 0), new Tile(3285, 3636, 0), new Tile(3285, 3639, 0), new Tile(3285, 3642, 0), new Tile(3285, 3645, 0), new Tile(3285, 3648, 0),
            new Tile(3288, 3650, 0), new Tile(3291, 3652, 0), new Tile(3294, 3652, 0), new Tile(3297, 3652, 0), new Tile(3300, 3652, 0), new Tile(3303, 3654, 0), new Tile(3303, 3657, 0), new Tile(3303, 3660, 0), new Tile(3302, 3663, 0), new Tile(3301, 3666, 0), new Tile(3300, 3669, 0), new Tile(3300, 3672, 0), new Tile(3300, 3675, 0), new Tile(3300, 3678, 0), new Tile(3300, 3681, 0), new Tile(3300, 3684, 0), new Tile(3300, 3687, 0), new Tile(3300, 3690, 0), new Tile(3301, 3693, 0), new Tile(3301, 3696, 0), new Tile(3301, 3699, 0), new Tile(3301, 3702, 0), new Tile(3302, 3705, 0), new Tile(3305, 3705, 0), new Tile(3308, 3705, 0), new Tile(3311, 3705, 0), new Tile(3314, 3708, 0), new Tile(3317, 3711, 0), new Tile(3319, 3714, 0), new Tile(3320, 3717, 0), new Tile(3323, 3720, 0), new Tile(3323, 3723, 0), new Tile(3323, 3726, 0), new Tile(3323, 3729, 0), new Tile(3323, 3732, 0), new Tile(3323, 3735, 0), new Tile(3323, 3738, 0), new Tile(3323, 3741, 0), new Tile(3323, 3744, 0), new Tile(3323, 3747, 0), new Tile(3323, 3750, 0), new Tile(3323, 3753, 0), new Tile(3323, 3756, 0), new Tile(3325, 3759, 0), new Tile(3327, 3762, 0),
            new Tile(3327, 3765, 0), new Tile(3327, 3768, 0), new Tile(3329, 3771, 0), new Tile(3329, 3774, 0), new Tile(3329, 3777, 0), new Tile(3329, 3780, 0), new Tile(3329, 3783, 0), new Tile(3327, 3786, 0), new Tile(3325, 3789, 0), new Tile(3325, 3792, 0), new Tile(3325, 3795, 0), new Tile(3325, 3798, 0), new Tile(3325, 3801, 0), new Tile(3325, 3804, 0),     new Tile(3323, 3807, 0), new Tile(3323, 3810, 0), new Tile(3323, 3813, 0), new Tile(3321, 3816, 0), new Tile(3318, 3816, 0), new Tile(3317, 3819, 0), new Tile(3315, 3822, 0), new Tile(3312, 3823, 0), new Tile(3309, 3826, 0), new Tile(3306, 3829, 0), new Tile(3303, 3832, 0), new Tile(3300, 3834, 0), new Tile(3298, 3837, 0), new Tile(3298, 3840, 0), new Tile(3298, 3843, 0), new Tile(3298, 3846, 0), new Tile(3298, 3849, 0), new Tile(3298, 3852, 0), new Tile(3300, 3855, 0), new Tile(3301, 3858, 0), new Tile(3303, 3860, 0)};


    private final Walker walker = new Walker(ctx);
    public WalkingToNats(ClientContext ctx) {super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(LAWRUNE_ID).count(true) > 1 && BankToWild2[0].distanceTo(ctx.players.local()) < 350
                && BankToWild2[BankToWild2.length-1].distanceTo(ctx.players.local())>2
                && ctx.players.local().tile().y() > 3521;


    }

    @Override
    public void execute() {
        ctx.input.speed((int)(Random.nextInt(50,68)*factorresizable));
        CurrentTask = "Walking To Nats";
        int random2 = org.powerbot.script.Random.nextInt(5,12);
        if(random2==8) {
            ctx.camera.pitch(org.powerbot.script.Random.nextInt(50, 99));
            ctx.camera.angle(org.powerbot.script.Random.nextInt(0, 360));

        }
        if(!ctx.movement.running() && ctx.movement.energyLevel() > org.powerbot.script.Random.nextInt(20,60)) {
            ctx.widgets.component(160,27).click();
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 9) {
            if (ctx.inventory.select().count() > 1) {

                walker.walkPath(BankToWild2);


            }
        }

    }
    //walker.walkPathReverse(BankToWild2);
}
