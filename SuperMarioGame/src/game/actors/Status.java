package game.actors;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    POWERSTAR, // use this status to pass to other classes that player has consumed powerStar
    FERTILE, // used to classify ground types upon which trees can be spawned
    FLOOR_BANNED, // Used to label actors that cannot enter the floor
    LAVA_BANNED, // Used to label actors that cannot enter the Lava
    ENGAGED, // used to label actors that have attacked or been attacked (they follow now)
    VALID_CORPSE, // used to label actors that should not be removed from the map once dead
    DORMANT, // used to label Koopa's that have been made dormant
    WRENCH, // used to label actors that hold a wrench
    RESET, // used to label ground types that have just had their reset method run
    CAN_SPAWN, // used to label ground types that can spawn (removed for 1 turn when actor resets)
    FIRE_ATTACK, //used to label bowser and those with the fire flower booster.
    FLY, // used to buff actors that can fly
    KEYHOLDER // used to buff actors that have held the key
}
