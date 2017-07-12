#include<stdio.h>
#define MAX_ROW 5
#define MAX_COL 5

typedef struct point{
	int row;
	int col;
} pt;
int maze[MAX_ROW][MAX_COL] = {
	0,1,1,0,0,
	0,1,0,1,0,
	0,0,0,1,0,
	0,0,1,1,0,
	0,0,0,0,0,
};
int top = 0, bottom = 0;
pt MazeMark[MAX_ROW * MAX_COL];

int push(pt maze){
	MazeMark[top++] = maze;
}

pt pop(){
	return MazeMark[bottom++];
}

int IsEmpty(){
	return top == bottom;
}

void VisitPoint(int row_get, int col_get){
	pt way = {row_get, col_get};
	push(way);
	maze[row_get][col_get] = 2;
}

int PrintMaze(){
	int i = 0, j = 0;
	for(i = 0; i < MAX_ROW; i++){
		for(j = 0; j < MAX_COL; j++){
			printf("%d,", maze[i][j]);
		}
		printf("\n");
	}
}
int main(){
	VisitPoint(0, 0);
	while(IsEmpty() != 1){
		pt way = pop();
		if((way.row == MAX_ROW - 1) && (way.col == MAX_COL - 1))
			break;
		if((way.row + 1 < MAX_ROW) && (maze[way.row + 1][way.col]) == 0)
			VisitPoint(way.row +1, way.col);
		if((way.row - 1 >= 0) && (maze[way.row - 1][way.col]) == 0)
			VisitPoint(way.row - 1, way.col);
		if((way.col + 1 < MAX_ROW) && (maze[way.row][way.col + 1]) == 0)
			VisitPoint(way.row, way.col +1);
		if((way.col - 1 >= 0) && (maze[way.row][way.col - 1]) == 0)
			VisitPoint(way.row, way.col - 1);
	}
	PrintMaze();	
}
