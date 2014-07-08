IMIE-Shifu
==========
This software is meant to manage a library for a french association "Clés d'Asie".

The source code is under WTF licence : do whatever you want with it (see LICENSE).

You'll need Apache's httpclient, httpcore and common-loggins from httpcomponents. 
You'll also need toedter's jcalendar 

 ** https://hc.apache.org/httpcomponents-client-ga/  **
 ** http://toedter.com/jcalendar/                    **

The design implements an MVC pattern, data is accessed by a complex DAO module.

Work in progress, this is the first beta and not all possible errors are catched and processed.

You may have a look on the Singleton class - shifu/core/Singleton.java - which I found on the web
(http://neutrofoton.com) and edited a little to fit my needs.

Some parts were coded in a big hurry and are really dirty (shifu/view/*) but Exception handling is quite good in the shifu/model part ;).

 ** The project was built on Netbeans 8.0 using JDK 1.8 ** 


Sorry for English readers but most comments are in French. We should have a look on that for the next iteration.


.delwiv
