package de.xiekang.zookeeper;

import de.xiekang.zookeeper.client.ZKManagerImpl;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZKManagerImpl zkManager = new ZKManagerImpl();
        //zkManager.create("/lastRunDate", "ThisIsAData".getBytes());
        System.err.println(zkManager.getZNodeData("/mainNode", true));
    }
}
