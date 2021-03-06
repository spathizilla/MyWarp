package me.taylorkelly.mywarp.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import me.taylorkelly.mywarp.MyWarp;
import me.taylorkelly.mywarp.utils.MatchList;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class manages all warps in the network. It provides all methods that are
 * needed when making changes on the warp-network.
 * 
 */
public class WarpManager {

    /**
     * This map represents the warp network. It stores all warps using their
     * name as key.
     */
    private Map<String, Warp> warpMap;

    public WarpManager() {
        warpMap = MyWarp.inst().getConnectionManager().getMap();
        MyWarp.logger().info(getSize() + " warps loaded.");
    }

    /**
     * Adds the given warp to the network
     * 
     * @param name
     *            the name of the warp
     * @param warp
     *            the warp
     */
    public void addWarp(String name, Warp warp) {
        warpMap.put(name, warp);
        MyWarp.inst().getConnectionManager().addWarp(warp);
    }

    /**
     * Creates a private warp owned by the given player at the his position and
     * calls {@link #addWarp(String, Warp)} to add it to the network
     * 
     * @param name
     *            the name of the warp
     * @param player
     *            the player
     */
    public void addWarpPrivate(String name, Player player) {
        Warp warp = new Warp(name, player, false);
        addWarp(name, warp);
    }

    /**
     * Creates a public warp owned by the given player at the his position and
     * calls {@link #addWarp(String, Warp)} to add it to the network. If Dynmap
     * is used, a marker is added
     * 
     * @param name
     *            the name of the warp
     * @param player
     *            the player
     */
    public void addWarpPublic(String name, Player player) {
        Warp warp = new Warp(name, player);
        addWarp(name, warp);

        if (MyWarp.inst().getWarpSettings().dynmapEnabled) {
            MyWarp.inst().getMarkers().addWarp(warp);
        }
    }

    /**
     * Removes the given warp from the network. Will also remove the marker if
     * needed.
     * 
     * @param warp
     *            the warp
     */
    public void deleteWarp(Warp warp) {
        warpMap.remove(warp.getName());
        MyWarp.inst().getConnectionManager().deleteWarp(warp);

        if (MyWarp.inst().getWarpSettings().dynmapEnabled) {
            MyWarp.inst().getMarkers().deleteWarp(warp);
        }
    }

    /**
     * Gets a {@link MatchList} with all warps accessible by the given player
     * and matching the given name (exactly or partly). Optionally a comparator
     * can be specified to control the internal sorting of the match-list
     * elements.
     * 
     * @param name
     *            the name of the warp
     * @param player
     *            the player
     * @param comperator
     *            the comparator or null for default sorting
     * @return a match list with warps matching the given criteria
     */
    public MatchList getMatches(String name, Player player, Comparator<Warp> comperator) {
        TreeSet<Warp> exactMatches = new TreeSet<Warp>(comperator);
        TreeSet<Warp> matches = new TreeSet<Warp>(comperator);

        for (Warp warp : warpMap.values()) {
            if (player != null && !warp.isUsable(player)) {
                continue;
            }
            if (warp.getName().equalsIgnoreCase(name)) {
                exactMatches.add(warp);
            } else if (warp.getName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(warp);
            }
        }
        if (exactMatches.size() > 1) {
            Iterator<Warp> iterator = exactMatches.iterator();
            while (iterator.hasNext()) {
                Warp warp = iterator.next();
                if (!warp.getName().equals(name)) {
                    matches.add(warp);
                    iterator.remove();
                }
            }
        }
        return new MatchList(exactMatches, matches);
    }

    /**
     * Attempts to match the given creator string to a creator who owns warps
     * usable by the given command-sender. This method will always return the
     * full name of the searched creator or null if there either no or more than
     * on match(es)
     * 
     * @param sender
     *            the command-sender
     * @param creator
     *            the (part of) the searched creator
     * @return the an exactly matching creator existing in the network or an
     *         null if not exact match exist
     */
    public String getMatchingCreator(CommandSender sender, String creator) {
        String match = null;

        for (Warp warp : warpMap.values()) {
            if (!warp.isUsable(sender)) {
                continue;
            }
            String warpCreator = warp.getCreator();

            // minecraft usernames are case insensitive
            if (warpCreator.equalsIgnoreCase(creator)) {
                return warpCreator;
            }
            if (!StringUtils.containsIgnoreCase(warpCreator, creator)) {
                continue;
            }
            if (match != null && !match.equals(warpCreator)) {
                return null;
            }
            match = warpCreator;
        }
        return match;
    }

    /**
     * Gets a sorted set with all warps matching the criteria in the network.
     * 
     * @param publicAll
     *            true if the warps should be public, false if private or null
     *            for all types.
     * @param creator
     *            the exact creator-name of the warps or null for warps by all
     *            creators
     * 
     * @return a set with all existing public warps
     */
    public TreeSet<Warp> getWarps(Boolean publicAll, String creator) {
        TreeSet<Warp> ret = new TreeSet<Warp>();

        for (Warp warp : warpMap.values()) {
            if ((publicAll == null || warp.isPublicAll() == publicAll)
                    && (creator == null || warp.isCreator(creator))) {
                ret.add(warp);
            }
        }
        return ret;
    }

    /**
     * gets the number of all warps in the network
     * 
     * @return the total number of warps
     */
    public int getSize() {
        return warpMap.size();
    }

    /**
     * gets the warp of the given name from the warp network. Will return null
     * if the warp does not exist.
     * 
     * @param name
     *            the warp's name
     * @return the warp with the given name
     */
    public Warp getWarp(String name) {
        return warpMap.get(name);
    }

    /**
     * Gets the number of all existing private warps created by the given
     * player, on the given worlds.
     * 
     * @param player
     *            the player
     * @param worlds
     *            a list of worlds the warp must be on to be counted, can be
     *            null
     * @return the number of all private warps
     */
    private int getPrivateWarpNumber(Player player, List<String> worlds) {
        int size = 0;
        for (Warp warp : warpMap.values()) {
            if (worlds != null && !worlds.contains(warp.getWorld())) {
                continue;
            }
            if (warp.isCreator(player) && !warp.isPublicAll()) {
                size++;
            }
        }
        return size;
    }

    /**
     * Gets the number of all existing public warps created by the given player,
     * on the given worlds.
     * 
     * @param player
     *            the player
     * @param worlds
     *            a list of worlds the warp must be on to be counted, can be
     *            null
     * @return the number of all public warps
     */
    private int getPublicWarpNumber(Player player, List<String> worlds) {
        int size = 0;
        for (Warp warp : warpMap.values()) {
            if (worlds != null && !worlds.contains(warp.getWorld())) {
                continue;
            }
            if (warp.isCreator(player) && warp.isPublicAll()) {
                size++;
            }
        }
        return size;
    }

    /**
     * Gets the number of all existing warps created by the given player, on the
     * given worlds.
     * 
     * @param player
     *            the player
     * @param worlds
     *            a list of worlds the warp must be on to be counted, can be
     *            null
     * @return the number of all warps
     */
    private int getTotalWarpNumber(Player player, List<String> worlds) {
        int size = 0;
        for (Warp warp : warpMap.values()) {
            if (worlds != null && !worlds.contains(warp.getWorld())) {
                continue;
            }
            if (warp.isCreator(player)) {
                size++;
            }
        }
        return size;
    }

    /**
     * Checks if the given player may build additional private warps using his
     * private-limit. This method does not take into account if limits are
     * enabled or not!
     * 
     * @param player
     *            the player
     * @return true if the player can build additional private warps, false if
     *         not
     */
    public boolean canBuildPrivateWarp(Player player) {
        // abort directly if the player can disobey limits on this world
        if (MyWarp.inst().getPermissionsManager()
                .hasPermission(player, "mywarp.limit.disobey." + player.getWorld().getName() + ".private")) {
            return true;
        }

        WarpLimit limit = MyWarp.inst().getPermissionsManager().getWarpLimit(player);
        List<String> affectedWorlds = new ArrayList<String>();
        for (String world : limit.getAffectedWorlds()) {
            // only count warps on worlds the player cannot disobey limits on
            if (!MyWarp.inst().getPermissionsManager()
                    .hasPermission(player, "mywarp.limit.disobey." + world + ".private")) {
                affectedWorlds.add(world);
            }
        }

        return getPrivateWarpNumber(player, affectedWorlds) < limit.getPrivateLimit();
    }

    /**
     * Checks if the given player may build additional warps using his
     * public-limit. This method does not take into account if limits are
     * enabled or not!
     * 
     * @param player
     *            the player
     * @return true if the player can build additional public warps, false if
     *         not
     */
    public boolean canBuildPublicWarp(Player player) {
        // abort directly if the player can disobey limits on this world
        if (MyWarp.inst().getPermissionsManager()
                .hasPermission(player, "mywarp.limit.disobey." + player.getWorld().getName() + ".public")) {
            return true;
        }

        WarpLimit limit = MyWarp.inst().getPermissionsManager().getWarpLimit(player);
        List<String> affectedWorlds = new ArrayList<String>();
        for (String world : limit.getAffectedWorlds()) {
            // only count warps on worlds the player cannot disobey limits on
            if (!MyWarp.inst().getPermissionsManager()
                    .hasPermission(player, "mywarp.limit.disobey." + world + ".public")) {
                affectedWorlds.add(world);
            }
        }

        return getPublicWarpNumber(player, affectedWorlds) < limit.getPublicLimit();
    }

    /**
     * Checks if the given player may build additional warps using his
     * total-limit. This method does not take into account if limits are enabled
     * or not!
     * 
     * @param player
     *            the player
     * @return true if the player can build additional warps, false if not
     */
    public boolean canBuildWarp(Player player) {
        // abort directly if the player can disobey limits on this world
        if (MyWarp.inst().getPermissionsManager()
                .hasPermission(player, "mywarp.limit.disobey." + player.getWorld().getName() + ".total")) {
            return true;
        }

        WarpLimit limit = MyWarp.inst().getPermissionsManager().getWarpLimit(player);
        List<String> affectedWorlds = new ArrayList<String>();
        for (String world : limit.getAffectedWorlds()) {
            // only count warps on worlds the player cannot disobey limits on
            if (!MyWarp.inst().getPermissionsManager()
                    .hasPermission(player, "mywarp.limit.disobey." + world + ".total")) {
                affectedWorlds.add(world);
            }
        }

        return getTotalWarpNumber(player, affectedWorlds) < limit.getTotalLimit();
    }

    /**
     * Sets the compass target of the given player to the location of the given
     * warp
     * 
     * @param warp
     *            the warp
     * @param player
     *            the player
     */
    public void point(Warp warp, Player player) {
        player.setCompassTarget(warp.getLocation());
    }

    /**
     * Checks if a warp with the given name exist
     * 
     * @param name
     *            the name
     * @return true if a warp with this name exists, false if not
     */
    public boolean warpExists(String name) {
        return warpMap.containsKey(name);
    }

    /**
     * gets a sorted set with all warps, the given command-sender has access to.
     * Optionally, this warps must be all created by the given creator or exist
     * in a world of the given name. The sorting of the warps can be controlled
     * by giving a custom comperator.
     * 
     * @param sender
     *            the command sender
     * @param creator
     *            the creator's name or null for all creators
     * @param world
     *            the world's name or null for all worlds
     * @param comperator
     *            the comperator or null for the default sorting
     * @return a sorted list with all warps matching the given criteria
     */
    public TreeSet<Warp> getUsableWarps(CommandSender sender, String creator, String world,
            Comparator<Warp> comperator) {
        TreeSet<Warp> results = new TreeSet<Warp>(comperator);

        if (creator != null) {
            creator = getMatchingCreator(sender, creator);

            // unable to find a matching creator
            if (creator == null) {
                return results;
            }
        }

        for (Warp warp : warpMap.values()) {
            if (!warp.isUsable(sender)) {
                continue;
            }
            if (creator != null && !warp.isCreator(creator)) {
                continue;
            }
            if (world != null && !warp.getWorld().equals(world)) {
                continue;
            }
            results.add(warp);
        }
        return results;
    }
}
