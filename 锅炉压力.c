#include <stdio.h>
#include <stdlib.h>

/**
�õ���¯��ѹ��ֵ
0~250֮��
*/ 
int getPressure(){
	int pressure = rand()%250;
	return pressure;
}

main(){
	
	while(1){
		_sleep(500);
		int pressure = getPressure();
		
		printf("��ǰ��¯ѹ��ֵ�ǣ�%d\n",pressure);
	}
	
	system("pause");
} 
