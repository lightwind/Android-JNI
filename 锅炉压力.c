#include <stdio.h>
#include <stdlib.h>

/**
得到锅炉的压力值
0~250之间
*/ 
int getPressure(){
	int pressure = rand()%250;
	return pressure;
}

main(){
	
	while(1){
		_sleep(500);
		int pressure = getPressure();
		
		printf("当前锅炉压力值是：%d\n",pressure);
	}
	
	system("pause");
} 
