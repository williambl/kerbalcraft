package com.williambl.kerbalcraft.common;

import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.KRPC;
import krpc.client.services.SpaceCenter;

import java.io.IOException;
import java.net.InetAddress;

public class RPCConnectionManager {

    private Connection rpcConn = null;
    private KRPC krpc = null;
    private SpaceCenter spaceCenter = null;

    public Connection getRpcConn() {
        return rpcConn;
    }

    public KRPC getKrpc() {
        return krpc;
    }

    public SpaceCenter getSpaceCenter() {
        return spaceCenter;
    }

    public KRPC connect(InetAddress address) {
        disconnect();
        try {
            rpcConn = Connection.newInstance("main", address);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        krpc = KRPC.newInstance(rpcConn);
        try {
            System.out.println("Connected to kRPC version " + krpc.getStatus().getVersion());
            spaceCenter = SpaceCenter.newInstance(rpcConn);
            return krpc;
        } catch (RPCException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        spaceCenter = null;
        krpc = null;
        rpcConn = null;
    }

    public boolean isConnected() {
        return krpc != null;
    }
}
