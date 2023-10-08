#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure that represents a magic square
typedef struct {
    int size;           // dimension of the square
    int **magic_square; // pointer to heap allocated magic square
} MagicSquare;

// 获取魔方的大小，确保它是大于或等于3的奇数。
int getSize() {
    int n;
    // 提示用户输入魔方的大小
    printf("输入魔方的大小（奇数整数 >=3）\n");
    scanf("%d", &n);
    // 检查输入是否为奇数并且大于等于3
    while (n < 3 || n % 2 == 0) {
        if (n < 3) {
            printf("魔方大小必须大于等于 3。\n");
        } else {
            printf("魔方大小必须是奇数。\n");
        }
        printf("输入魔方的大小（奇数整数 >=3）\n");
        scanf("%d", &n);
    }
    return n;
}

// 使用“连体法”算法生成大小为n的魔方
MagicSquare *generateMagicSquare(int n) {
    // 动态分配魔方结构的内存
    MagicSquare *ms = (MagicSquare *) malloc(sizeof(MagicSquare));
    ms->size = n;
    // 动态分配矩阵的行
    ms->magic_square = (int **) malloc(n * sizeof(int *));
    for (int i = 0; i < n; i++) {
        // 动态分配每行的列
        *(ms->magic_square + i) = (int *) malloc(n * sizeof(int));
    }

    int num = 1;  // 从1开始填充
    int i = 0, j = n / 2;  // 初始化为顶部中间位置
    // 循环直到魔方中填充了所有的数字（即从1到n^2）
    while (num <= n * n) {
        // 在当前位置放置数字
        *(*(ms->magic_square + i) + j) = num++;

        // 默认情况下，我们将沿着右上方向移动
        i--;
        j++;

        // 边界和冲突处理：
        // 如果我们达到了正方形的左上角
        if (i < 0 && j >= n) {
            // 移动到正下方
            i += 2;
            j--;
        }
            // 如果我们在正方形的上边界
        else if (i < 0) {
            // 我们“绕回”到最底部
            i = n - 1;
        }
            // 如果我们在正方形的右边界
        else if (j >= n) {
            // 我们“绕回”到最左边
            j = 0;
        }
            // 如果当前位置已经有数字了（冲突）
        else if (*(*(ms->magic_square + i) + j) != 0) {
            // 我们移动到当前位置的正下方
            i += 2;
            j--;
        }
    }

    return ms;
}

// 将魔方写入指定的文件
void fileOutputMagicSquare(MagicSquare *magic_square, char *filename) {
    // 打开输出文件
    FILE *fp = fopen(filename, "w");
    if (!fp) {
        printf("Error opening file.\n");
        exit(1);
    }
    // 写入魔方的大小
    fprintf(fp, "%d\n", magic_square->size);
    for (int i = 0; i < magic_square->size; i++) {
        for (int j = 0; j < magic_square->size; j++) {
            // 写入魔方中的每个数字
            fprintf(fp, "%d", *(*(magic_square->magic_square + i) + j));
            if (j != magic_square->size - 1) fprintf(fp, ",");
        }
        fprintf(fp, "\n");
    }
    fclose(fp);  // 关闭文件
}

// 主函数，组织程序流程
int main(int argc, char **argv) {
    // 检查命令行参数是否正确
    if (argc != 2) {
        printf("使用方法： ./my_magic_square <输出文件名>。\n");
        return 1;
    }

    // 从用户那里获取魔方的大小
    int n = getSize();
    // 生成魔方
    MagicSquare *ms = generateMagicSquare(n);
    // 将魔方输出到文件
    fileOutputMagicSquare(ms, argv[1]);

    // 释放所有动态分配的内存
    for (int i = 0; i < n; i++) {
        free(*(ms->magic_square + i));
    }
    free(ms->magic_square);
    free(ms);

    return 0;
}

