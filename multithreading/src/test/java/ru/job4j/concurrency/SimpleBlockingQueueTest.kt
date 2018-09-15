package ru.job4j.concurrency

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.RepeatedTest

internal class SimpleBlockingQueueTest {
    private val queue = SimpleBlockingQueue<Int>()

    inner class Consumer constructor(private val storage: SimpleBlockingQueue<Int>) : Thread() {
        override fun run() {
            this.storage.poll()
        }
    }

    inner class Producer constructor(private val storage: SimpleBlockingQueue<Int>, private val value: Int) : Thread() {
        override fun run() {
            this.storage.offer(value)
        }
    }

    @RepeatedTest(10)
    fun whenConcurrencyThenProducer() {
        val consumer = Consumer(queue)
        val producer = Producer(queue, 1)
        producer.start()
        producer.join()
        assertThat(queue.queue.size, `is`(1))
        consumer.start()
        consumer.join()
        assertThat(queue.queue.size, `is`(0))
    }
}