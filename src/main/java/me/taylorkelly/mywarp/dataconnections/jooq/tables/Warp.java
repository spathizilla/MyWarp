/**
 * This class is generated by jOOQ
 */
package me.taylorkelly.mywarp.dataconnections.jooq.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = { "http://www.jooq.org", "3.3.1" }, comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Warp extends
        org.jooq.impl.TableImpl<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord> {

    private static final long serialVersionUID = -451981696;

    /**
     * The singleton instance of <code>mywarp.warp</code>
     */
    public static final me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp WARP = new me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp();

    /**
     * The class holding records for this type
     */
    @Override
    public java.lang.Class<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord> getRecordType() {
        return me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord.class;
    }

    /**
     * The column <code>mywarp.warp.warp-id</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, org.jooq.types.UInteger> WARP_ID = createField(
            "warp-id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.name</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.String> NAME = createField(
            "name", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.player-id</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, org.jooq.types.UInteger> PLAYER_ID = createField(
            "player-id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.x</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.Double> X = createField(
            "x", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.y</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.Double> Y = createField(
            "y", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.z</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.Double> Z = createField(
            "z", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.pitch</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.Float> PITCH = createField(
            "pitch", org.jooq.impl.SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.yaw</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.Float> YAW = createField(
            "yaw", org.jooq.impl.SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.world-id</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, org.jooq.types.UInteger> WORLD_ID = createField(
            "world-id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.creation-date</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.sql.Timestamp> CREATION_DATE = createField(
            "creation-date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>mywarp.warp.type</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, me.taylorkelly.mywarp.data.Warp.Type> TYPE = createField(
            "type",
            org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false).asConvertedDataType(
                    new me.taylorkelly.mywarp.dataconnections.converter.TypeConverter()), this, "");

    /**
     * The column <code>mywarp.warp.visits</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, org.jooq.types.UInteger> VISITS = createField(
            "visits", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaulted(true), this, "");

    /**
     * The column <code>mywarp.warp.fee</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.Double> FEE = createField(
            "fee", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>mywarp.warp.welcome-message</code>.
     */
    public final org.jooq.TableField<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, java.lang.String> WELCOME_MESSAGE = createField(
            "welcome-message", org.jooq.impl.SQLDataType.CLOB.length(255), this, "");

    /**
     * Create a <code>mywarp.warp</code> table reference
     */
    public Warp() {
        this("warp", null);
    }

    /**
     * Create an aliased <code>mywarp.warp</code> table reference
     */
    public Warp(java.lang.String alias) {
        this(alias, me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp.WARP);
    }

    private Warp(java.lang.String alias,
            org.jooq.Table<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord> aliased) {
        this(alias, aliased, null);
    }

    private Warp(java.lang.String alias,
            org.jooq.Table<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord> aliased,
            org.jooq.Field<?>[] parameters) {
        super(alias, me.taylorkelly.mywarp.dataconnections.jooq.Mywarp.MYWARP, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.Identity<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, org.jooq.types.UInteger> getIdentity() {
        return me.taylorkelly.mywarp.dataconnections.jooq.Keys.IDENTITY_WARP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.jooq.UniqueKey<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord> getPrimaryKey() {
        return me.taylorkelly.mywarp.dataconnections.jooq.Keys.KEY_WARP_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.List<org.jooq.UniqueKey<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord>> getKeys() {
        return java.util.Arrays
                .<org.jooq.UniqueKey<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord>> asList(
                        me.taylorkelly.mywarp.dataconnections.jooq.Keys.KEY_WARP_PRIMARY,
                        me.taylorkelly.mywarp.dataconnections.jooq.Keys.KEY_WARP_U_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.List<org.jooq.ForeignKey<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, ?>> getReferences() {
        return java.util.Arrays
                .<org.jooq.ForeignKey<me.taylorkelly.mywarp.dataconnections.jooq.tables.records.WarpRecord, ?>> asList(
                        me.taylorkelly.mywarp.dataconnections.jooq.Keys.FK_WARP_PLAYER,
                        me.taylorkelly.mywarp.dataconnections.jooq.Keys.FK_WARP_WORLD1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp as(java.lang.String alias) {
        return new me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp(alias, this);
    }

    /**
     * Rename this table
     */
    public me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp rename(java.lang.String name) {
        return new me.taylorkelly.mywarp.dataconnections.jooq.tables.Warp(name, null);
    }
}
