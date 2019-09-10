### cloudpaas-all
*at oracle.jdbc.xa.OracleXAResource.recover(OracleXAResource.java:703)*
可以通过 sqlplus / as sysdba 把xa授权给用户
```
grant select on sys.dba_pending_transactions to <user name>;
grant select on sys.pending_trans$ to <user name>;
grant select on sys.dba_2pc_pending to <user name>;
grant execute on sys.dbms_system to <user name>;
```