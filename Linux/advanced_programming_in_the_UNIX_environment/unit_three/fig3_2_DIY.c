#include "apue.h"
#include <fcntl.h>

char	buf1[] = "abcdefghij";
char	buf2[] = "ABCDEFGHIJ";
char	buf3[16394] = "0";

int
main(void)
{
	int	fd1, fd2;

	if ((fd1 = creat("file.hole", FILE_MODE)) < 0)
		err_sys("creat error");

	if ((fd2 = creat("file.nohole", FILE_MODE)) < 0)
		err_sys("creat error");

	if (write(fd1, buf1, 10) != 10)
		err_sys("buf1 write error");
	/* offset now = 10 */

	if (write(fd2, buf3, 16394) != 16394)
		err_sys("NOHOLE write error");

	if (lseek(fd1, 16384, SEEK_SET) == -1)
		err_sys("lseek error");
	/* offset now = 16384 */

	if (write(fd1, buf2, 10) != 10)
		err_sys("buf2 write error");
	/* offset now = 16394 */

	exit(0);
}
