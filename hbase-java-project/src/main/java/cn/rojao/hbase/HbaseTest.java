package cn.rojao.hbase;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Date:2019/3/28
 * Author:jianxian mao
 * Describe:
 */
public class HbaseTest {

    private Connection connection;

    @Before
    public void init() throws IOException {
        Configuration configuration = new Configuration();
        // 配置ZooKeeper信息
        configuration.set("hbase.zookeeper.quorum", "node-1:2181");
        // 创建连接
        connection = ConnectionFactory.createConnection(configuration);
    }

    @Test
    public void testCreateTable() throws IOException {
        Admin admin = connection.getAdmin();
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf("tb_user"));

        ColumnFamilyDescriptorBuilder userInfo = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("user_info"));
        userInfo.setMaxVersions(3);
        tableDescriptorBuilder.setColumnFamily(userInfo.build());


        ColumnFamilyDescriptorBuilder loginInfo = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("login_info"));
        tableDescriptorBuilder.setColumnFamily(loginInfo.build());

        admin.createTable(tableDescriptorBuilder.build());

        System.out.println("创建表"+"tb_user"+"成功");
    }

    @Test
    public void testPut() throws IOException {
        Table table = connection.getTable(TableName.valueOf("tb_user"));
        String rowkey = "1001";
        Put put = new Put(Bytes.toBytes(rowkey));

        put.addColumn(Bytes.toBytes("user_info"),Bytes.toBytes("name"),Bytes.toBytes("张三"));
        put.addColumn(Bytes.toBytes("user_info"),Bytes.toBytes("address"),Bytes.toBytes("广州市"));

        put.addColumn(Bytes.toBytes("login_info"),Bytes.toBytes("username"),Bytes.toBytes("zhangsan"));
        put.addColumn(Bytes.toBytes("login_info"),Bytes.toBytes("password"),Bytes.toBytes("123456"));

        table.put(put);
        table.close();
        System.out.println("插入数据成功");
    } String rowkey = "1001";

    @Test
    public void testGet() throws IOException {
        Table table = connection.getTable(TableName.valueOf("tb_user"));
        String rowkey = "1001";
        Get get = new Get(Bytes.toBytes(rowkey));


        Result result = table.get(get);
        List<Cell> cells = result.listCells();

        printReult(result);
        table.close();
    }
    @Test
    public void testScan() throws IOException {
        Table table = connection.getTable(TableName.valueOf("tb_user"));

        Scan scan = new Scan();
        scan.setLimit(1); //只查询一条数据
        ResultScanner scanner = table.getScanner(scan);
        Result result = null;

        while ((result = scanner.next())!= null){
            printReult(result);

        }
    }

    private void printReult(Result result) {
        List<Cell> cells = result.listCells();

        for (Cell cell : cells) {
            byte[] row = CellUtil.cloneRow(cell);
            byte[] family = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            byte[] value = CellUtil.cloneValue(cell);
            System.out.println(Bytes.toString(row)+"=>"+Bytes.toString(family)+"=>"+Bytes.toString(qualifier)+"=>"+Bytes.toString(value));
        }
    }


}
