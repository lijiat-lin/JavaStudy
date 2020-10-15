# Java NIO概述

Java NIO 的主要核心组件：

- Channel
- Buffer
- Selector

> Channel

通道将数据读取到缓存区中，而缓存区将数据写入到channel中。

![Java NIO：通道和缓冲区](image/java-nio/overview-channels-buffers.png)

Java NIO中常见的Channel

- FileChannel
- DatagramChannel
- SocketChannel
- ServerSocketChannel

这些channel覆盖了UDP和TCP的网络IO以及文件IO

> Buffer

Java NIO 中常见的Buffer

- ByteBuffer
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

> Selector

Selector允许单个线程处理多个Channel。

![Java NIO：选择器](image/java-nio/overview-selectors.png)

# Java NIO Channel