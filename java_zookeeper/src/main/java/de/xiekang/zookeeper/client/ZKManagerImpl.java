package de.xiekang.zookeeper.client;

import de.xiekang.zookeeper.ZKConnection;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ZKManagerImpl implements ZKManager {
    private static ZooKeeper zooKeeper;
    private static ZKConnection zkConnection;

    public ZKManagerImpl() throws IOException, InterruptedException {
        initialize();
    }

    private void initialize() throws IOException, InterruptedException {
        zkConnection = new ZKConnection();
        zooKeeper = zkConnection.connect("localhost");
    }

    public void closeConnection() throws InterruptedException {
        zkConnection.close();
    }

    @Override
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        // info: once created, the ZNode is persistent and doesn't get deleted when the client disconnects.
        zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Override
    public Object getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        byte[] b = null;
        b = zooKeeper.getData(path, null, null);
        return new String(b, "UTF-8");
    }

    @Override
    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zooKeeper.exists(path, true).getVersion();
        zooKeeper.setData(path, data, version);
    }
}
