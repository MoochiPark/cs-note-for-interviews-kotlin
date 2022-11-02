package ch01

interface Subject {
    fun register(obj: Observer)
    fun unregister(obj: Observer)
    fun notifyObservers()
    fun getUpdate(obj: Observer): String
}

interface Observer {
    fun update()
}

class Topic(
    private val observers: MutableList<Observer> = mutableListOf(),
    private var message: String = "",
) : Subject {
    override fun register(obj: Observer) {
        if (!observers.contains(obj)) observers.add(obj)
    }

    override fun unregister(obj: Observer) {
        observers.remove(obj)
    }

    override fun notifyObservers() {
        this.observers.forEach(Observer::update)
    }

    override fun getUpdate(obj: Observer): String {
        return this.message
    }

    fun postMessage(message: String) {
        println("Message sent to Topic $message")
        this.message = message
        notifyObservers()
    }
}

class TopicSubscriber(
    private val name: String,
    private val topic: Subject,
) : Observer {
    override fun update() {
        val message = topic.getUpdate(this)
        println("$name:: get message >> $message")
    }
}

fun main() {
    val topic = Topic()
    val a = TopicSubscriber("a", topic)
    val b = TopicSubscriber("b", topic)
    val c = TopicSubscriber("c", topic)
    topic.register(a)
    topic.register(b)
    topic.register(c)

    topic.postMessage("hello world~")
}