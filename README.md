# Mamamoo Hotel

para sa ITE-12 CS21S1 Final Project

---

_mga shit na importante_

## MS SQL Server 2008 Configurations:
1. Ensure that MS SQL Server 2008 R2 is installed together with SQL Server Browser
2. Ensure **[KB2979597](https://www.microsoft.com/en-us/download/details.aspx?id=44271)** update is installed. (required for KB4057113)
   > Use the installer `SQLServer2008R2SP3-KB2979597-x64-ENU.exe` found in `/sysResources/SqlServerUpdate.zip`
3. Ensure **[KB4057113](https://www.microsoft.com/en-us/download/details.aspx?id=56415)** update is installed. (See [problem](https://stackoverflow.com/questions/69623611/how-do-i-allow-java-client-tls10-connections))
   > Use the installer `SQLServer2008R2-KB4057113-x64.exe` found in `/sysResources/SqlServerUpdate.zip`
4. Add the `mssql-jdbc_auth-12.6.1.x64.dll` binary to `C:\Program Files\Java\jdk-17\bin` or any `\bin` directory of the currently used JDK. (See [problem](https://stackoverflow.com/questions/61117080/no-mssql-jdbc-auth-8-2-1-x64-in-java-library-path))
   > The binary can be found in `/sysResources`  
   > Can also be downloaded [here](https://go.microsoft.com/fwlink/?linkid=2262747) and found in `\sqljdbc_12.6\enu\auth\x64\` 
5. Enable TCP/IP of SQL Server in Sql Server Manager and add port `1434` to IPAll (See [problem](https://stackoverflow.com/questions/18841744/jdbc-connection-failed-error-tcp-ip-connection-to-host-failed))  
   _Extended instructions below_
6. Enable SQL Server Browser in Sql Server Configuration Manager

## Enabling TCP/IP of SQL Server and port `1434`
1. Open Sql Server Configuration Manager
2. Expand the SQL Server Network Configuration in navigation sidebar
3. Select **"Protocols for `<target DB server name>`"**
4. In the main window, right click **_TCP/IP_** then click **_Enable_**
5. Right click it again, then click the **_IP Addresses_** tab.
6. Scroll down to the bottom and find **_IPAll_** group.
7. Enter `1434` in the **_TCP Port_**
8. Click **_Apply_** then **_OK_**
9. Restart the SQL Server