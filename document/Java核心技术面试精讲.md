# 模块一 Java基础

## 第1讲 | 谈谈你对Java平台的理解？

> 标准回答

1. Java本身是一种面向对象语言，最显著的特性有两个方面
   - “书写一次，到处运行”（Write once，run anywhere），能够非常容易的获取跨平台能力
   - 垃圾收集（GC，Garbage Collection），Java通过垃圾回收器（GC）回收分配内存，大部分情况下，程序员不需要自己操心内存的分配和回收
2. Java的运行环境
   - JRE（Java Runtime Environment），Java运行环境，包含JVM和java类库，以及一些模块等
   - JDK（Java Development Kit），可以看做JRE的超集，提供了更多的工具，比如编译器各种诊断工具等
3. “Java是解释执行”的理解
   - java的源代码，首先会通过javac编译成字节码（bytecode）
   - 然后再运行时，通过Java虚拟机（JVM）内嵌的`解释器`将`字节码`解释转换成最终的`机器码`
   - 但是常见的JVM，例如Oracle JDK提供的Hotspot JVM，提供了JIT（Just In Time）编译器，就是通常所说的`动态编译器`
   - JIT能够在运行的时候，将`热点代码`编译成`机器码`，这种情况下部分的热点代码就属于`编译执行`，而不再是解释执行了

> 知识拓展

对于java平台的理解，可以从其他方面简明扼要的描述，例如

- Java语言特性：包括泛型，Lambda等语言特性
- 基础类库：包括集合，IO/NIO，网络，并发，安全等基础类库
- JVM的基础概念还有机制：java类的加载机制，常用JDK版本内嵌的Class-Loader，垃圾回收器的原理等

![img](image/javaPoint/java平台.png)



> java的解释执行和编译执行

java通常被分为编译期和运行时，javac将java源代码编译成“.class”文件，文件中是字节码，然后运行在jvm虚拟机上。Java通过字节码和Java虚拟机（JVM）这种跨平台的抽象，屏蔽了操作系统和硬件的细节，这个也是实现“一次编译，到处运行”的基础。



在运行时，java会通过类加载器（Class-Loader）加载字节码，解释或者编译执行。

- 在主流Java的版本中，如JDK8实际是解释和编译混合的一种模式，即所谓的混合模式（-Xmixed）。
- 通常运行在server模式的JVM，会进行成千上万次调用以收集足够的信息进行高效的编译，
- client模式的门限是1500次
- Oracle HotSpot JVM 内置了两个不同的 JIT compiler
  - C1模式对应client模式，适用于对于启动速度敏感的应用，比如普通的java桌面应用
  - C2对应server模式，它的优化是为了长时间运行的服务端应用设置的



JVM虚拟机启动的时候，可能指定不同的参数对运行模式进行选择

- 指定 “-Xint” ，就是告诉JVM只能解释执行，不对代码进行编译，这种模式抛弃是JIT可能带来的性能优势。毕竟解释器（interpreter）是逐条读入的，逐条解释运行的。
- “-Xcomp”，告诉JVM关闭解释器，不要进行解释执行，或者叫做最大优化级别。但是这个方式会导致JVM启动变慢了很多。同时有些JIT编译器优化方式，比如分支预测，如果不进行profiling（剖析），往往并不能进行有效优化。

除了常用的java的使用模式（混合模式，解释执行，编译执行），还有一种新的编译方式，就是AOT（Ahead-of-Time Compilation），直接将字节码编译成机器代码，避免了JIT的预热的各方面的开销。比如Oracle JDK 9就引入实验性的AOT特性，并且增加了jaotc工具。利用一下命令可以把某个类或者某个模块编译成AOT库

```java

jaotc --output libHelloWorld.so HelloWorld.class
jaotc --output libjava.base.so --module java.base
```

然后在启动的时候指定即可

```java

java -XX:AOTLibrary=./libHelloWorld.so,./libjava.base.so HelloWorld
```

而且，Oracle JDK 支持分层编译和 AOT 协作使用，这两者并不是二选一的关系。

参考相关文档：http://openjdk.java.net/jeps/295



> 对于解释器和编译器的理解

https://www.cnblogs.com/zhanghongqiang/p/4431216.html

**Java编译器（javac.exe）**: 将java源程序编译成中间代码字节码文件（.class文件），是最基本的开发工具

**Java解释器（java.exe）**: 又译为`直译器`是一种电脑程序，能够把高级编译语言一行一行直接转译运行。解释器不会一次把整个程序转译出来，而是每次运行程序的时候都要先转成另一种语言再作运行，因此解释器的运行速度比较慢。它是需要一行一行的转译程序。

Java应用程序的开发周期包括编译、下载 、解释和执行几个部分

- **Java编译程序**将Java源程序翻译为JVM可执行的字节码。
- **Java编译器**却不将对变量和方法的引用编译为数值引用，也不确定程序执行过程中的内存布局，而是将这些符号引用信息保留在字节码中，由解释器在运行过程中创立内存布局，然后再通过查表来确定一个方法所在的地址。这样就有效的保证了Java的可移植性和安全性。
- 运行JVM字节码的工作是由**解释器**来完成的。解释执行过程分三部进行：`代码的装入`、`代码的校验`和`代码的执行`。



Java字节码的执行有两种方式：

- 即时编译方式：解释器先将字节码编译成机器码，然后再执行该机器码。
- 解释执行方式：解释器通过每次解释并执行一小段代码来完成Java字节码程 序的所有操作。
- 通常采用的是第二种方法。由于JVM规格描述具有足够的灵活性，这使得将字节码翻译为机器代码的工作具有较高的效率。



> 精华理解

需要强调的一点是，java并不是编译机制，而是解释机制。Java字节码的设计充分考虑了JIT这一即时编译方式，可以将字节码直接转化成高性能的本地机器码，这同样是虚拟机的一个构成部分。

Java特性：

- 面向对象（封装，继承，多态）
- 平台无关性（JVM运行.Class文件）
- 语言（泛型，Lambda表达式）
- 类库（集合，并发，网络，IO/NIO）
- JRE（Java运行环境，JVM，类库）
- JDK（Java开发工具，包括JRE，javac，诊断工具）





Java是解析运行么？

​	不正确

- Java源代码经过javac编译成字节码文件（.class文件）
- .class文件再经过JVM解释或者编译运行
  - 解释：.class文件经过JVM内嵌的解释器解释执行
  - 编译：存在JIT（Just In Time Compiler 即时编译器）把经常运行的代码作为`热点代码`编译与本地平台相关的机器码，并进行各种层次的优化
  - AOT编译器：Java 9 提供的 直接将所有代码编译成机器码执行

## 第2讲 | Exception和Error有什么区别？

> 典型回答

Exception和Error都是集成Throwable类，在java中只有Throwable类型的实例才可以被抛出（throw）或者捕获（catch），它是异常处理机制的基本组成类型。

Error是指绝大情况下，不可能出现的情况。绝大部分的Error会导致程序（比如JVM自身）处于非正常的、不可恢复的状态，既然是非正常的情况，所以不便于也不需要进行捕捉，常见的比如说OutOfMemoryError之类，都是Error的子类。

Exception是程序正在运行中，可以预料的意外情况，可能并且应该被捕获，进行相应的处理。

Exception又分为可检查（checked）异常和不可检查（unchecked）异常

- 可检查异常在源代码里必须显示的进行捕获处理，这是编译期检查的一部分
- 不可检查异常就是所谓的运行时异常，RuntimeExecpiton,除此异常外都是可检查异常。类似NullPointerException、ArrayIndexOutOfMemoryError之类，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译期强制要求



> 考点分析

**1、理解Throwable，Exception、Error的设计和分类**

Throwable常见子类

- Exception
  - NoSuchFileException（找不到字段）
  - InstantiationException（实例化异常）
  - NoSuchMethodException（找不到方法）
  - ClassNotFountException（找不到类）
  - SQLException（SQL异常）
  - IOException（IO异常）
    - EOFException（文件已结束）
    - FileNotFoundException（文件未找到）
  - RuntimeException
    - StringIndexOutOfBoundsException（字符串索引越界）
    - ClassCastException（类型转换异常）
    - ArrayIndexOutOfMemoryError（数组索引越界）
    - SecurityException（安全异常）
    - NullPointException（空指针异常）
    - NumberFormatException（转换数字异常）
- Error
  - ThreadDeath（线程死亡）
  - VirtualMachineError（虚拟机错误）
    - UnknownError（未知错误）
    - OutOfMemoryError（内存溢出错误）
    - StackOverflowError（栈溢出错误）
    - InternalError（内部错误）
      - ZipError（Zip错误）

**2、NoClassDefFoundError 和 ClassNotFoundException 有什么区别？**

当应用程序运行的过程中尝试使用类加载器去加载Class文件的时候，如果没有在classpath中查找到指定的类，就会抛出ClassNotFoundException。

一般情况下，当我们使用Class.forName()或者ClassLoader.loadClass以及使用ClassLoader.findSystemClass()在运行时加载类的时候，如果类没有被找到，那么就会导致JVM抛出ClassNotFoundException。



当JVM在加载一个类的时候，如果这个类在编译时是可用的，但是在运行时找不到这个类的定义的时候，JVM就会抛出一个NoClassDefFoundError错误。比如当我们在new一个类的实例的时候，如果在运行是类找不到，则会抛出一个NoClassDefFoundError的错误。

3、针对异常的处理方式

try-catch-finally 块，throw、throws

> 知识扩展

**异常处理的基本原则**

- 尽量不要捕获类似Exception这样的通用异常，而是应该捕获特定异常。保证程序不会捕获到我们不希望捕获的异常。
- 不要生吞（swallow）异常，就是不要在catch之后不做出任处理，这样会导致出现问题后很难去判断错误究竟在哪。
- 捕获异常之后尽量不要使用`e.printStackTrace();`，会导致很难判断异常到底输出到哪里去了。



**Throw early，catch late原则**

所谓的**Throw early**原则指的是"让错误尽早被抛出"，不要等到我们的代码执行到一半再抛出异常，这样很有可能导致一部分的变量处于异常状态，从而引发出更多的错误

```java

/**
 *
 * 针对fileName为null的情况应该在使用这个参数之前就进行校验，并且抛出异常
 */
public void readPreferences(String fileName){
    //...perform operations...
    InputStream in = new FileInputStream(fileName);
    //...read the preferences file...
}
```

**Catch late**原则的意思是捕获异常后，如果不知道如何处理，应该选择保留原有异常的cause信息，直接选择抛出或者构成新的异常抛出。等到了更高的层面再选择捕获处理。
 原因在于，到了更高的层面，我们的业务逻辑会变得更加清晰，这个时候我们会更清楚合适的处理方法。



**从性能角度分析Java的异常处理：**

- try-catch代码段会产生额外的性能开销，或者换个角度说，它往往会影响JVM对代码进行优化，所以建议仅捕获有必要的代码段，尽量不要一个大的try包住整段的代码。
- Java 每实例化一个 Exception，都会对当时的栈进行快照，这是一个相对比较重的操作。如果发生的非常频繁，这个开销可就不能被忽略了。

> 问题思考：

对于异常处理编程，不同的编程范式也会影响到异常处理策略，比如，现在非常火热的反应式编程（Reactive Stream），因为其本身是异步、基于事件机制的，所以出现异常情况，决不能简单抛出去。可以有什么好的方式处理？



另外，由于代码堆栈不再是同步调用那种垂直的结构，这里的异常处理和日志需要更加小心，我们看到的往往是特定 executor 的堆栈，而不是业务方法调用关系。对于这种情况，有什么好的方式处理？



## 第3讲 | 谈谈final、finally、 finalize有什么不同？

> 典型回答

final用来修饰类，方法，变量。final修饰的类代表不可以集成，final修饰的变量代表不可以修改，而final修饰的方法表示不可以重写。

finally则是Java保证重点代码一定要被执行的一种机制。我们可以使用try-finally或者try-catch-finally来进行类似关闭JDBC链接，保证unlock锁等动作。

finalize是基础类java.lang.Obect的一个方法。它的设计目的是保证对象在垃圾收集之前完成特定资源的回收。finalize机制现在已经不推荐使用，并且在JDK9开始被标记为的deprecated。

> 考点分析

考察你对性能、并发、对象生命周期或垃圾收集基本过程等方面的理解。

推荐使用 final 关键字来明确表示我们代码的语义、逻辑意图，这已经被证明在很多场景下是非常好的实践

- 我们可以将方法声明为final，这样就可以明确的告知别人，这些行为是不许修改的
- 使用final修饰参数或者变量，可以清楚地避免意外赋值导致的编程错误，甚至有人明确推荐将所有的方法参数、本地变量、成员变量生命成final
- final变量产生了某种程度的不可变（immutable）的效果，所以，可以用于保护只读数据，尤其是在并发编程中，因为明确的不能再赋值final的值，有利于减少额外的同步开销，可以省去一些防御性拷贝的必要

对于 finally，明确知道怎么使用就足够了。需要关闭的连接等资源，更推荐使用 Java 7 中添加的 try-with-resources 语句，因为通常 Java 平台能够更好地处理异常情况

对于 finalize，我们要明确它是不推荐使用的，业界实践一再证明它不是个好的办法，在 Java 9 中，甚至明确将 Object.finalize() 标记为 deprecated！如果没有特别的原因，不要实现 finalize 方法，也不要指望利用它来进行资源回收。



> 知识扩展

**1、final不是immutable**

例如以下代码：

```java
 final List<String> strList = new ArrayList<>();
 strList.add("Hello");
 strList.add("world");  
//List.of()方法创建的本身就是不可变的，所以unmodifiableStrList.add 就会抛出异常
 List<String> unmodifiableStrList = List.of("hello", "world");
 unmodifiableStrList.add("again");
```

final只能够约束strList这个引用不可以被复制，但是strList对象的行为不会被final影响，依然可以继续add添加元素。

如果真的希望对象本身不是可变的，那么需要相应的类支持不可变的行为。



**2、finalize真的这么不堪？**

finalize 的执行是和垃圾收集关联在一起的，一旦实现了非空的 finalize 方法，就会导致相应对象回收呈现数量级上的变慢

finalize 被设计成在对象被垃圾收集前调用，这就意味着实现了 finalize 方法的对象是个“特殊公民”，JVM 要对它进行额外处理。finalize 本质上成为了快速回收的阻碍者，可能导致你的对象经过多个垃圾收集周期才能被回收。

从另一个角度，我们要确保回收资源就是因为资源都是有限的，垃圾收集时间的不可预测，可能会极大加剧资源占用。这意味着对于消耗非常高频的资源，千万不要指望 finalize 去承担资源释放的主要职责，最多让 finalize 作为最后的“守门员”，况且它已经暴露了如此多的问题。



3、有什么机制可以替换finalize码？

Java 平台目前在逐步使用 java.lang.ref.Cleaner 来替换掉原有的 finalize 实现。**Cleaner** 的实现利用了幻象引用（PhantomReference），这是一种常见的所谓 post-mortem 清理机制。

吸取了 finalize 里的教训，每个 Cleaner 的操作都是独立的，它有自己的运行线程，所以可以避免意外死锁等问题。

> 引申思考

**1、什么是immutable？**



**2、Cleaner的大致内容？**



## 第4讲 | 强引用、软引用、弱引用、幻象引用有什么区别？

> 典型回答

不同的引用类型，主要体现的是**对象不同的可达性（reachable）状态和垃圾收集的影响**

**强引用（“Strong” Reference）**

- 最常见的普通对象引用，只要还有强引用指向一个对象，那么这个对象就表示还“活着”，垃圾收集器就不会碰这种对象。对
- 于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式的将相应（强）引用复制为null，就是可以被垃圾收集了，具体回收时机还是要看垃圾收集策略。

**软引用（Soft Reference）**

- 是一种相对强引用弱化了一些的引用，可以让一些对象豁免垃圾收集，只有当JVM认为内存不足的时候，才会去视图回收软引用所指向的对象。
- JVM会在抛出OutOfMemoryError之前，清理所有软引用对象。
- 软引用通常用来实现内存敏感的缓存，如果内存还有空闲，就可以暂时保留缓存，当内存不足时清理掉，这样就保证了使用缓存的同事，不会耗尽内存。

**弱引用（Weak Reference）**

- 并不能使对象豁免垃圾收集，仅仅是提供了一种访问在弱引用状态下对象的途径，这就可以用来构建一种没有特定约束的关系
- 比如，维护一种非强制的映射关系，如果试图获取对象的时候，对象还在就使用它，对象不在就重新实例化。它同样是很多缓存实现的选择

虚引用/幻象引用（Phantom reference）

- 不能通过它访问对象。
- 虚引用仅仅提供了一种确保对象被finalize之后，做某些事情的机制
- 比如通常用来做所谓的Post-Mortem清理机制

> 知识扩展

**1、对象可达性状态流转分析**

![img](image/javaPoint/对象可达性状态流转分析.png)

该图定义了Java的不同可达级别（reachability level）

- 强可达（Strongly Reachable），就是当一个对象可以有一个或者多个线程可以不通过各种引用就可以访问到的情况。比如新创建一个对象，那么创建它的线程对它就是强可达
- 软可达（Softly Reachable），就是当我们通过软引用才能够访问到的对象
- 弱可达（Weakly Reachable），就是无法通过强引用或者软引用访问到，只能通过弱引用才能访问到的状态。这是十分临近 finalize 状态的时机，当弱引用被清除的时候，就符合 finalize 的条件了。
- 幻象可达（Phantom Reachable），上面流程图已经很直观了，就是没有强、软、弱引用关联，并且 finalize 过了，只有幻象引用指向这个对象的时候。
- 当然，还有一个最后的状态，就是不可达（unreachable），意味着对象可以被清除了。

所有引用类型，都是抽象类 java.lang.ref.Reference 的子类，它提供了get（）方法

```
T		get()		Returns this reference object's referent
```

除了幻象引用（因为gei永远返回的是null），如果对象还没有被销毁，都可以通过 get 方法获取原有对象。

这意味着，利用软引用和弱引用，我们可以将访问到的对象，重新指向强引用，也就是人为的改变了对象的可达性状态！

所以，对于软引用、弱引用之类，垃圾收集器可能会存在二次确认的问题，以保证处于弱引用状态的对象，没有改变为强引用。



**2. 引用队列（ReferenceQueue）使用**

谈到各种引用的编程，就必然要提到引用队列。

我们在创建各种引用并关联到相应对象时，可以选择是否需要关联引用队列，JVM 会在特定时机将引用 enqueue 到队列里，我们可以从队列里获取引用（remove 方法在这里实际是有获取的意思）进行相关后续逻辑。

尤其是幻象引用，get 方法只返回 null，如果再不指定引用队列，基本就没有意义了。

示例代码中，利用引用队列，我们可以在对象处于相应状态时（对于幻象引用），执行后期处理逻辑。

```java
//创建一个新的对象
        Object object = new Object();
        //创建一个引用队列
        ReferenceQueue referenceQueue = new ReferenceQueue();
        //将对象放入到引用队列中
        PhantomReference<Object> p = new PhantomReference<>(object,referenceQueue);
        //去除object对象的强引用
        object = null;
        //执行垃圾回收
        System.gc();
        try{

            Reference<Object> ref = referenceQueue.remove(1000L);
            if(ref!=null){
                //do something
            }
        }catch (InterruptedException e){

        }
```

**3、显式地影响软引用垃圾收集**

软引用通常会在最后一次引用后，还能保持一段时间，默认值是根据堆剩余空间计算的（以 M bytes 为单位）。从 Java  1.3.1 开始，提供了 -XX:SoftRefLRUPolicyMSPerMB 参数，我们可以以毫秒（milliseconds）为单位设置。比如，下面这个示例就是设置为 3 秒（3000 毫秒）。

```
-XX:SoftRefLRUPolicyMSPerMB=3000
```

**4、诊断 JVM 引用情况**

如果你怀疑应用存在引用（或 finalize）导致的回收问题，可以有很多工具或者选项可供选择，比如 HotSpot JVM 自身便提供了明确的选项（PrintReferenceGC）去获取相关信息，我指定了下面选项去使用 JDK 8 运行一个样例应用：

```

-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintReferenceGC
```

这是 JDK 8 使用 ParrallelGC 收集的垃圾收集日志，各种引用数量非常清晰。

```

0.403: [GC (Allocation Failure) 0.871: [SoftReference, 0 refs, 0.0000393 secs]0.871: [WeakReference, 8 refs, 0.0000138 secs]0.871: [FinalReference, 4 refs, 0.0000094 secs]0.871: [PhantomReference, 0 refs, 0 refs, 0.0000085 secs]0.871: [JNI Weak Reference, 0.0000071 secs][PSYoungGen: 76272K->10720K(141824K)] 128286K->128422K(316928K), 0.4683919 secs] [Times: user=1.17 sys=0.03, real=0.47 secs] 
```

**注意：JDK 9 对 JVM 和垃圾收集日志进行了广泛的重构，类似 PrintGCTimeStamps 和 PrintReferenceGC 已经不再存在**



## 第5讲 | String、StringBuffer、StringBuilder有什么区别？

> 典型回答

String 是 Java 语言非常基础和重要的类，提供了构造和管理字符串的各种基本逻辑。它是典型的 Immutable 类，被声明成为 final class，所有属性也都是 final 的。也由于它的不可变性，类似拼接、裁剪字符串等动作，都会产生新的 String 对象。由于字符串操作的普遍性，所以相关操作的效率往往对应用性能有明显影响。



StringBuffer 是为解决上面提到拼接产生太多中间对象的问题而提供的一个类，我们可以用 append 或者 add 方法，把字符串添加到已有序列的末尾或者指定位置。StringBuffer 本质是一个**线程安全**的可修改字符序列，它保证了线程安全，也随之带来了额外的性能开销，所以除非有线程安全的需要，不然还是推荐使用它的后继者，也就是 StringBuilder。

StringBuilder 是 Java 1.5 中新增的，在能力上和 StringBuffer 没有本质区别，但是它去掉了线程安全的部分，有效减小了开销，是绝大部分情况下进行字符串拼接的首选。



> 知识扩展

**1、字符串设计和实现考量**

String 是 Immutable 类的典型实现，原生的保证了基础线程安全，因为你无法对它内部数据进行任何修改，这种便利甚至体现在拷贝构造函数中，由于不可变，Immutable 对象在拷贝时不需要额外复制数据。

StringBuffer 实现的一些细节，它的线程安全是通过把各种修改数据的方法都加上 synchronized 关键字实现的，非常直白。其实，这种简单粗暴的实现方式，非常适合我们常见的线程安全类实现，不必纠结于 synchronized 性能之类的，有人说“过早优化是万恶之源”，考虑可靠性、正确性和代码可读性才是大多数应用开发最重要的因素。

为了实现修改字符序列的目的，StringBuffer 和 StringBuilder 底层都是利用可修改的（char，JDK 9 以后是 byte）数组，二者都继承了 AbstractStringBuilder，里面包含了基本操作，区别仅在于最终的方法是否加了 synchronized。

目前的实现是，构建时初始字符串长度加 16（这意味着，如果没有构建对象时输入最初的字符串，那么初始值就是 16）。我们如果确定拼接会发生非常多次，而且大概是可预计的，那么就可以指定合适的大小，避免很多次扩容的开销。扩容会产生多重开销，因为要抛弃原有数组，创建新的（可以简单认为是倍数）数组，还要进行 arraycopy。



**2、字符串缓存**

String 在 Java 6 以后提供了 intern() 方法，目的是提示 JVM 把相应字符串缓存起来，以备重复使用。在我们创建字符串对象并调用 intern() 方法的时候，如果已经有缓存的字符串，就会返回缓存里的实例，否则将其缓存起来。一般来说，JVM 会将所有的类似“abc”这样的文本字符串，或者字符串常量之类缓存起来。

一般使用 Java 6 这种历史版本，并不推荐大量使用 intern，为什么呢？魔鬼存在于细节中，被缓存的字符串是存在所谓 PermGen 里的，也就是臭名昭著的“永久代”，这个空间是很有限的，也基本不会被 FullGC 之外的垃圾收集照顾到。所以，如果使用不当，OOM 就会光顾。

在后续版本中，这个缓存被放置在堆中，这样就极大避免了永久代占满的问题，甚至永久代在 JDK 8 中被 MetaSpace（元数据区）替代了。而且，默认缓存大小也在不断地扩大中，从最初的 1009，到 7u40 以后被修改为 60013。

幸好在 Oracle JDK 8u20 之后，推出了一个新的特性，也就是 G1 GC 下的字符串排重。它是通过将相同数据的字符串指向同一份数据来做到的，是 JVM 底层的改变，并不需要 Java 类库做什么修改。



3、String 自身的演化

如果你仔细观察过 Java 的字符串，在历史版本中，它是使用 char 数组来存数据的，这样非常直接。但是 Java 中的 char 是两个 bytes 大小，拉丁语系语言的字符，根本就不需要太宽的 char，这样无区别的实现就造成了一定的浪费。密度是编程语言平台永恒的话题，因为归根结底绝大部分任务是要来操作数据的。

在 Java 9 中，我们引入了 Compact Strings 的设计，对字符串进行了大刀阔斧的改进。将数据存储方式从 char 数组，改变为一个 byte 数组加上一个标识编码的所谓 coder，并且将相关字符串操作类都进行了修改。另外，所有相关的 Intrinsic 之类也都进行了重写，以保证没有任何性能损失。

在通用的性能测试和产品实验中，我们能非常明显地看到紧凑字符串带来的优势，即`更小的内存占用、更快的操作速度`。

## 第6讲 | 动态代理是基于什么原理？

> 典型回答

反射机制是 Java 语言提供的一种基础功能，赋予程序在运行时自省（introspect，官方用语）的能力。通过反射我们可以直接操作类或者对象，比如获取某个对象的类定义，获取类声明的属性和方法，调用方法或者构造对象，甚至可以运行时修改类定义。

动态代理是一种方便运行时动态构建代理、动态处理代理方法调用的机制，很多场景都是利用类似机制做到的，比如用来包装 RPC 调用、面向切面的编程（AOP）。

实现动态代理的方式很多，比如 JDK 自身提供的动态代理，就是主要利用了上面提到的反射机制。还有其他的实现方式，比如利用传说中更高性能的字节码操作机制，类似 ASM、cglib（基于 ASM）、Javassist 等。

> 考点分析

功能才是目的，实现的方法有很多。总的来说，这道题目考察的是 Java 语言的另外一种基础机制： 反射，它就像是一种魔法，引入运行时自省能力，赋予了 Java 语言令人意外的活力，通过运行时操作元数据或对象，Java 可以灵活地操作运行时才能确定的信息。而动态代理，则是延伸出来的一种广泛应用于产品开发中的技术，很多繁琐的重复编程，都可以被动态代理机制优雅地解决

从考察知识点的角度，这道题涉及的知识点比较庞杂，所以面试官能够扩展或者深挖的内容非常多，比如：

- 考察你对反射机制的了解和掌握程度。
- 动态代理解决了什么问题，在你业务系统中的应用场景是什么？
- JDK 动态代理在设计和实现上与 cglib 等方式有什么不同，进而如何取舍？

> 知识扩展

**1、反射机制及其演进**

关于反射，有一点我需要特意提一下，就是反射提供的 AccessibleObject.setAccessible(boolean flag)。它的子类也大都重写了这个方法，这里的所谓 accessible 可以理解成修饰成员的 public、protected、private，这意味着我们可以在运行时修改成员访问限制！



setAccessible 的应用场景非常普遍，遍布我们的日常开发、测试、依赖注入等各种框架中。比如，在 O/R Mapping 框架中，我们为一个 Java 实体对象，运行时自动生成 setter、getter 的逻辑，这是加载或者持久化数据非常必要的，框架通常可以利用反射做这个事情，而不需要开发者手动写类似的重复代码。

另一个典型场景就是绕过 API 访问控制。我们日常开发时可能被迫要调用内部 API 去做些事情，比如，自定义的高性能 NIO 框架需要显式地释放 DirectBuffer，使用反射绕开限制是一种常见办法。

在 Java 9 以后，这个方法的使用可能会存在一些争议，因为 Jigsaw 项目新增的模块化系统，出于强封装性的考虑，对反射访问进行了限制。Jigsaw 引入了所谓 Open 的概念，只有当被反射操作的模块和指定的包对反射调用者模块 Open，才能使用 setAccessible；否则，被认为是不合法（illegal）操作。

因为反射机制使用广泛，根据社区讨论，目前，Java 9 仍然保留了兼容 Java 8 的行为，但是很有可能在未来版本，完全启用前面提到的针对 setAccessible 的限制，即只有当被反射操作的模块和指定的包对反射调用者模块 Open，才能使用 setAccessible，我们可以使用下面参数显式设置。

```

--illegal-access={ permit | warn | deny }
```

**2、动态代理**

首先，动态代理是一个代理机制。如果熟悉设计模式中的代理模式，我们会知道，代理可以看作是对调用目标的一个包装，这样我们对目标代码的调用不是直接发生的，而是通过代理完成。

通过代理可以让调用者与实现者之间解耦。比如进行 RPC 调用，框架内部的寻址、序列化、反序列化等，对于调用者往往是没有太大意义的，通过代理，可以提供更加友善的界面。

我们可以看 JDK 动态代理的一个简单例子。下面只是加了一句 print，在生产系统中，我们可以轻松扩展类似逻辑进行诊断、限流等。

```java

public class MyDynamicProxy {
    public static  void main (String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        // 调用代理方法
        proxyHello.sayHello();
    }
}
interface Hello {
    void sayHello();
}
class HelloImpl implements  Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello World");
    }
}
 class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("Invoking sayHello");
        Object result = method.invoke(target, args);
        return result;
    }
}

```

上面的 JDK Proxy 例子，非常简单地实现了动态代理的构建和代理操作。首先，实现对应的 InvocationHandler；然后，以接口 Hello 为纽带，为被调用目标构建代理对象，进而应用程序就可以使用代理对象间接运行调用目标的逻辑，代理为应用插入额外逻辑（这里是 println）提供了便利的入口。

从 API 设计和实现的角度，这种实现仍然有局限性，因为它是以接口为中心的，相当于添加了一种对于被调用者没有太大意义的限制。我们实例化的是 Proxy 对象，而不是真正的被调用类型，这在实践中还是可能带来各种不便和能力退化。

JDK Proxy 的优势：

- 最小化依赖关系，减少依赖意味着简化开发和维护，JDK 本身的支持，可能比 cglib 更加可靠。
- 平滑进行 JDK 版本升级，而字节码类库通常需要进行更新以保证在新版 Java 上能够使用。
- 代码实现简单。

基于类似cglib框架的优势：

- 有的时候调用目标可能不便实现额外接口，从某种角度看，限定调用者实现接口是有些侵入性的实践，类似 cglib 动态代理就没有这种限制。
- 只操作我们关心的类，而不必为其他相关类增加工作量。
- 高性能。



## 第7讲 | int和Integer有什么区别？

> 典型回答

int 是我们常说的整形数字，是 Java 的 8 个原始数据类型（Primitive Types，boolean、byte 、short、char、int、float、double、long）之一。Java 语言虽然号称一切都是对象，但原始数据类型是例外。

Integer 是 int 对应的包装类，它有一个 int 类型的字段存储数据，并且提供了基本操作，比如数学运算、int 和字符串之间转换等。在 Java 5 中，引入了自动装箱和自动拆箱功能（boxing/unboxing），Java 可以根据上下文，自动进行转换，极大地简化了相关编程。

关于 Integer 的值缓存，这涉及 Java 5 中另一个改进。构建 Integer 对象的传统方式是直接调用构造器，直接 new 一个对象。但是根据实践，我们发现大部分数据操作都是集中在有限的、较小的数值范围，因而，在 Java 5 中新增了静态工厂方法 valueOf，在调用它的时候会利用一个缓存机制，带来了明显的性能改进。按照 Javadoc，这个值默认缓存是 -128 到 127 之间。

> 知识扩展

**1、理解自动装箱，拆箱**

自动装箱实际上算是一种语法糖。什么是语法糖？可以简单理解为 Java 平台为我们自动进行了一些转换，保证不同的写法在运行时等价，它们发生在编译阶段，也就是生成的字节码是一致的。

像前面提到的整数，javac 替我们自动把装箱转换为 Integer.valueOf()，把拆箱替换为 Integer.intValue()，这似乎这也顺道回答了另一个问题，既然调用的是 Integer.valueOf，自然能够得到缓存的好处啊。

原则上，建议避免无意中的装箱、拆箱行为，尤其是在性能敏感的场合，创建 10 万个 Java 对象和 10 万个整数的开销可不是一个数量级的，不管是内存使用还是处理速度，光是对象头的空间占用就已经是数量级的差距了。

**2、源码分析**

整体看一下 Integer 的职责，它主要包括各种基础的常量，比如最大值、最小值、位数等；前面提到的各种静态工厂方法 valueOf()；获取环境变量数值的方法；各种转换方法，比如转换为不同进制的字符串，如 8 进制，或者反过来的解析方法等。我们进一步来看一些有意思的地方。

首先，继续深挖缓存，Integer 的缓存范围虽然默认是 -128 到 127，但是在特别的应用场景，比如我们明确知道应用会频繁使用更大的数值，这时候应该怎么办呢？

缓存上限值实际是可以根据需要调整的，JVM 提供了参数设置：

```
-XX:AutoBoxCacheMax=N
```

这些实现，都体现在java.lang.Integer源码之中，并实现在 IntegerCache 的静态初始化块里。

```java

private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];
        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =                VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            ...
            // range [-128, 127] must be interned (JLS7 5.1.7)
            assert IntegerCache.high >= 127;
        }
        ...
  }
```

> 问题思考

java对象的内存结构是什么样的？

- 在HotSpot虚拟机中，对象在内存中存储的布局可以分为3块区域：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。
- HotSpot虚拟机的对象头包括两部分信息，第一部分用于存储对象自身的运行时数据，如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时间戳等，这部分数据的长度在32位和64位的虚拟机（未开启压缩指针）中分别为32bit和64bit，官方称它为"Mark Word"。
- 对象头的另外一部分是类型指针，即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。
- 第三部分对齐填充并不是必然存在的，也没有特别的含义，它仅仅起着占位符的作用。由于HotSpot VM的自动内存管理系统要求对象起始地址必须是8字节的整数倍，换句话说，就是对象的大小必须是8字节的整数倍。



如何计算或者获取某个java对象的大小？



## 第8讲 | 对比Vector、ArrayList、LinkedList有何区别？

> 典型回答

这三者都是实现集合框架中的 List，也就是所谓的有序集合，因此具体功能也比较近似，比如都提供按照位置进行定位、添加或者删除的操作，都提供迭代器以遍历其内容等。但因为具体的设计区别，在行为、性能、线程安全等方面，表现又有很大不同。

`Vector` 是 Java 早期提供的`线程安全的动态数组`，如果不需要线程安全，并不建议选择，毕竟同步是有额外开销的。Vector 内部是使用对象数组来保存数据，可以根据需要自动的增加容量，当数组已满时，会创建新的数组，并拷贝原有数组数据。

`ArrayList `是应用更加广泛的`动态数组`实现，它本身`不是线程安全的`，所以性能要好很多。与 Vector 近似，ArrayList 也是可以根据需要调整容量，不过两者的调整逻辑有所区别，Vector 在扩容时会提高 1 倍，而 ArrayList 则是增加 50%

`LinkedList `顾名思义是 Java 提供的`双向链表`，所以它不需要像上面两种那样调整容量，它也不是线程安全的。



> 知识扩展

![img](image/javaPoint/集合框架.png)



我们可以看到 Java 的集合框架，Collection 接口是所有集合的根，然后扩展开提供了三大类集合，分别是：

- List，也就是我们前面介绍最多的有序集合，它提供了方便的访问、插入、删除等操作。
- Set，Set 是不允许重复元素的，这是和 List 最明显的区别，也就是不存在两个对象 equals 返回 true。我们在日常开发中有很多需要保证元素唯一性的场合。
- Queue/Deque，则是 Java 提供的标准队列结构的实现，除了集合的基本功能，它还支持类似先入先出（FIFO， First-in-First-Out）或者后入先出（LIFO，Last-In-First-Out）等特定行为。这里不包括 BlockingQueue，因为通常是并发编程场合，所以被放置在并发包里。



