
void getW_name(char waddr[],char wname[])
{
	int i,k=0,j,w_len=strlen(waddr);
	for(i=w_len-1;i>=0;i--)
	{
		if(waddr[i]=='\\')
			break;
		wname[k++]=waddr[i];
	}
	wname[k++]='\0';
	i=strlen(wname);
	for(j=0,k=i-1;j<i/2;j++,k--)
	{
		char temp=wname[j];wname[j]=wname[k];wname[k]=temp;
	}
}



void LongToChar(long x,char a[])
{
	int count=0;
	if(x==0)
		return;
	while(x)
	{
		a[count++]=x%10+'0';
		x=x/10;
	}
	a[count]='\0';
	for(int i=0,j=count-1;i<count/2;i++,j--)
	{ char temp;
	  temp=a[i];
	  a[i]=a[j];
	  a[j]=temp;}
}

long StrToLong(char str[])
{
	long result;
	bool bEnd;
	bEnd = true;
	result = 0;
	for ( int i = 0; bEnd; i++ )
	{
		if (str[i] != '\0' && str[i] >= '0' && str[i] <= '9')
		{
			result = result * 10 + str[i] - '0';
		}
		if (str[i] == '\0')
		{
			bEnd = false;
		}
	}
	return result;
}
