//package org.firstinspires.ftc.teamcode.opmode.auto.Config;
//
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.util.MatchOpMode;
//
//import java.util.Locale;
//
//@Autonomous
//public class AutoPathPlanning extends MatchOpMode {
//    public static final AutoChoices autoChoices = new AutoChoices();
//
//    public enum Alliance
//    {
//        RED_ALLIANCE,
//        BLUE_ALLIANCE
//    }
//
//    public enum StartPos
//    {
//        LEFT,
//        RIGHT
//    }
//    public enum AutoStrategy
//    {
//        LEFT_TRUSS,
//        MID_TRUSS,
//        RIGHT_TRUSS
//    }   //enum AutoStrategy
//    public static class AutoChoices {
//        public double delay = 0.0;
//        public Alliance alliance = Alliance.RED_ALLIANCE;
//        public StartPos startPos = StartPos.LEFT;
//        public AutoStrategy strategy = AutoStrategy.MID_TRUSS;
//        public double xTarget = 0.0;
//        public double yTarget = 0.0;
//        public double turnTarget = 0.0;
//
//        @Override
//        public String toString() {
//            return String.format(
//                    Locale.US,
//                    "delay=%.0f " +
//                            "alliance=\"%s\" " +
//                            "startPos=\"%s\" " +
//                            "strategy=\"%s\" " +
//                            "xTarget=%.1f " +
//                            "yTarget=%.1f " +
//                            "turnTarget=%.0f " +
//                            "driveTime=%.0f " +
//                            "drivePower=%.1f",
//                    delay, alliance, startPos, strategy, xTarget, yTarget, turnTarget);
//        }   //toString
//
//    }
//    @Override
//    public void robotInit() {
//
//    }
//
//    @Override
//    public void matchStart() {
//
//    }
//    private void doAutoChoicesMenus()
//    {
//        FtcValueMenu delayMenu = new FtcValueMenu("Delay time:", null, 0.0, 30.0, 1.0, 0.0, " %.0f sec");
//        FtcChoiceMenu<Alliance> allianceMenu = new FtcChoiceMenu<>("Alliance:", delayMenu);
//        FtcChoiceMenu<StartPos> startPosMenu = new FtcChoiceMenu<>("Start Position:", allianceMenu);
//        FtcChoiceMenu<AutoStrategy> strategyMenu = new FtcChoiceMenu<>("Auto Strategies:", startPosMenu);
//
//        FtcValueMenu xTargetMenu = new FtcValueMenu(
//                "xTarget:", strategyMenu, -12.0, 12.0, 0.5, 4.0, " %.1f ft");
//        FtcValueMenu yTargetMenu = new FtcValueMenu(
//                "yTarget:", xTargetMenu, -12.0, 12.0, 0.5, 4.0, " %.1f ft");
//        FtcValueMenu turnTargetMenu = new FtcValueMenu(
//                "turnTarget:", yTargetMenu, -180.0, 180.0, 5.0, 90.0, " %.0f deg");
//        FtcValueMenu driveTimeMenu = new FtcValueMenu(
//                "Drive time:", strategyMenu, 0.0, 30.0, 1.0, 5.0, " %.0f sec");
//        FtcValueMenu drivePowerMenu = new FtcValueMenu(
//                "Drive power:", strategyMenu, -1.0, 1.0, 0.1, 0.5, " %.1f");
//
//        // Link Value Menus to their children.
//        delayMenu.setChildMenu(allianceMenu);
//        xTargetMenu.setChildMenu(yTargetMenu);
//        yTargetMenu.setChildMenu(turnTargetMenu);
//        turnTargetMenu.setChildMenu(drivePowerMenu);
//        driveTimeMenu.setChildMenu(drivePowerMenu);
//        //
//        // Populate choice menus.
//        //
//        telemetry.addData("Red", Alliance.RED_ALLIANCE, true, startPosMenu);
//        allianceMenu.addChoice("Blue", Alliance.BLUE_ALLIANCE, false, startPosMenu);
//
//        startPosMenu.addChoice("Start Position Left", StartPos.LEFT, true, strategyMenu);
//        startPosMenu.addChoice("Start Position Right", StartPos.RIGHT, false, strategyMenu);
//
//        strategyMenu.addChoice("PID Drive", AutoStrategy.PID_DRIVE, false, xTargetMenu);
//        strategyMenu.addChoice("Timed Drive", AutoStrategy.TIMED_DRIVE, false, driveTimeMenu);
//        strategyMenu.addChoice("Do nothing", AutoStrategy.DO_NOTHING, true);
//        //
//        // Traverse menus.
//        //
//        FtcMenu.walkMenuTree(delayMenu);
//        //
//        // Fetch choices.
//        //
//        autoChoices.delay = delayMenu.getCurrentValue();
//        autoChoices.alliance = allianceMenu.getCurrentChoiceObject();
//        autoChoices.startPos = startPosMenu.getCurrentChoiceObject();
//        autoChoices.strategy = strategyMenu.getCurrentChoiceObject();
//        autoChoices.xTarget = xTargetMenu.getCurrentValue();
//        autoChoices.yTarget = yTargetMenu.getCurrentValue();
//        autoChoices.turnTarget = turnTargetMenu.getCurrentValue();
//        //
//        // Show choices.
//        //
//    }   //doAutoChoicesMenus
//}
