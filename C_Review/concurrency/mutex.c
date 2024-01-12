#include <pthread.h>
#include <stdio.h>

pthread_rwlock_t rwlock;

void* reader(void* arg) {
    pthread_rwlock_rdlock(&rwlock);
    printf("Reader is reading\n");
    // 执行读取操作
    pthread_rwlock_unlock(&rwlock);
    return NULL;
}

void* writer(void* arg) {
    pthread_rwlock_wrlock(&rwlock);
    printf("Writer is writing\n");
    // 执行写入操作
    pthread_rwlock_unlock(&rwlock);
    return NULL;
}

int main() {
    pthread_t t1, t2;

    // 初始化读写锁
    pthread_rwlock_init(&rwlock, NULL);

    // 创建线程进行读写
    pthread_create(&t1, NULL, reader, NULL);
    pthread_create(&t2, NULL, writer, NULL);

    // 等待线程完成
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    // 销毁读写锁
    pthread_rwlock_destroy(&rwlock);

    return 0;
}
