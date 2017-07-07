#define UP_1 1	//如果按照升序进行排列则为1，否则为-1

int bubble (int n, int date[]){
	int j = 0, i = 0, tmp = 0;
	for(j = 0; j < n; j++){	//determin the max sort times can be realized by wile(flag), when the sort action not apply the flag = 0;
		for(i = 0; i < n -1; i++){
			if(UP_1 * (date[i] - date[i+1]) < 0){
					tmp = date[i];
					date[i] = date[i+1];
					date[i+1] = tmp;
			}
		}
	}
}

int bubble_optimized (int n, int date[]){
	int i = 0, tmp = 0, flag, sort_end = n;
	while(flag){
		flag = 0;	//set 0, if no sort the while will stop	
		for(i = 0; i < sort_end -1; i++){
			if(UP_1 * (date[i] - date[i+1]) < 0){
					tmp = date[i];
					date[i] = date[i+1];
					date[i+1] = tmp;
					flag = 1;
					sort_end = i;	//record the max end number
			}
		}
	}
}
