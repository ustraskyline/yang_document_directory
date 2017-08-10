1、压缩文件中的每一个压缩实体都使用ZipEntry 保存，一个压缩文件中可能包含一个或多个的ZipEntry 对象。
2、在Java中可以进行zip、jar、gz、三种格式的压缩支持，操作流程基本上是一样的
3、ZipOutputStream 可以进行压缩输出，但是输出的位置不一定是文件。
4、ZipFile 表示每一个压缩文件，可以得到每一个压缩实体的输入流
5、ZipInputStream 可以得到每一个实体，但是却无法得到每一个实体的输入流。
6.  JDK中的ZipInputStream与ZipOutputStream 在压缩与解压缩操作中容易出现乱码，建议大家使用Apache的ZipInputStream与ZipOutputStream，因为它可以设置字符编码charset，这样就能够保证在写入/读取文件时，最大限度的避免乱码问题的发生！

详细文档在：
http://blog.csdn.net/dabing69221/article/details/17074763