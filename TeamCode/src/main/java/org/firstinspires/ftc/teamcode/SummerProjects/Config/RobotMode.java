//package org.firstinspires.ftc.teamcode.opmode.auto.Config;
//
//public class RobotMode
//{
//    public enum RunMode
//    {
//        /**
//         * The robot is only in this mode very briefly during initialization.
//         */
//        INVALID_MODE,
//
//        /**
//         * In FRC, the robot is in this mode before competition starts and between mode transitions from Autonomous
//         * to TeleOp, for example. (i.e. DISABLED_MODE to AUTO_MODE to DISABLED_MODE to TELEOP_MODE to DISABLED_MODE).
//         * This mode does not exist in FTC.
//         */
//        DISABLED_MODE,
//
//        /**
//         * The robot is in this mode during the autonomous period.
//         */
//        AUTO_MODE,
//
//        /**
//         * The robot is in this mode during the operator control period.
//         */
//        TELEOP_MODE,
//
//        /**
//         * The robot is in this mode when Test Mode is selected on the DriverStation.
//         */
//        TEST_MODE
//
//    }   //enum RunMode
//    public interface Mode {
//        /**
//         * This method is called on the main robot thread when the competition mode is about to start. In FTC, this
//         * is called when the "Play" button on the Driver Station is pressed. Typically, you put code that will
//         * prepare the robot for start of competition here such as resetting the encoders/sensors and enabling some
//         * sensors to start sampling.
//         *
//         * @param prevMode specifies the previous RunMode it is coming from.
//         * @param nextMode specifies the next RunMode it is going into.
//         */
//        void startMode(RunMode prevMode, RunMode nextMode);
//
//        /**
//         * This method is called on the main robot thread when competition mode is about to end. Typically, you put
//         * code that will do clean up here such as disabling the sampling of some sensors.
//         *
//         * @param prevMode specifies the previous RunMode it is coming from.
//         * @param nextMode specifies the next RunMode it is going into.
//         */
//        void stopMode(RunMode prevMode, RunMode nextMode);
//
//        /**
//         * This method is called on the main robot thread periodically at PERIODIC_INTERVAL. Typically, you put the
//         * competition code here that processes sensor/gamepad inputs and controls the movement of the robot. Code
//         * that doesn't require high frequency processing should observe the slowPeriodicLoop parameter and only
//         * runs when it is true (e.g. TeleOp control and robot status updates).
//         *
//         * @param elapsedTime      specifies the elapsed time since the mode started.
//         * @param slowPeriodicLoop specifies true if it is running the slow periodic loop on the main robot thread,
//         *                         false otherwise.
//         */
//        void periodic(double elapsedTime, boolean slowPeriodicLoop);
//    }
//}   //interface RobotMode
