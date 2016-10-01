package android.z10.com.threadpoolexecutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Trong này sẽ có 10 luồng, nhưng chỉ đặt maxPool = 5, mọi người qan sát kết quả và
 * đọc code.
 */
public class MainActivity extends AppCompatActivity {
    ThreadPoolExecutor executor;
    TextView txtThread1, txtThread2, txtThread3, txtThread4, txtThread5, txtThread6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        executor = new ThreadPoolExecutor(
                2, 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
        runThread1();
        runThread2();
        runThread3();
        runThread4();
        runThread5();
        runThread6();
    }

    private void bindViews() {
        txtThread1 = (TextView) findViewById(R.id.txtThread1);
        txtThread2 = (TextView) findViewById(R.id.txtThread2);
        txtThread3 = (TextView) findViewById(R.id.txtThread3);
        txtThread4 = (TextView) findViewById(R.id.txtThread4);
        txtThread5 = (TextView) findViewById(R.id.txtThread5);
        txtThread6 = (TextView) findViewById(R.id.txtThread6);
    }

    int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0;

    private void runThread1() {
        // Sau 1 giay thi cap nhat 1 lan, sau 11 lan thi stop
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        i1++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtThread1.setText("Thread 1 = " + i1);
                            if (i1 == 11) {
                                txtThread1.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                    if (i1 == 11) {
                        break;
                    }
                }
            }
        });
    }

    private void runThread2() {
// Sau 2 giay thi cap nhat 1 lan, sau 10lan thi stop
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        i2++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtThread2.setText("Thread 2 = " + i2);
                            if (i2 == 10) {
                                txtThread2.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                    if (i2 == 10) {
                        break;
                    }
                }
            }
        });
    }

    private void runThread3() {
        // Sau 3 giay thi cap nhat 1 lan, sau 7 lan thi stop
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        i3++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtThread3.setText("Thread 3 " + i3);
                            if (i3 == 7) {
                                txtThread3.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                    if (i3 == 7) {
                        break;
                    }
                }
            }
        });
    }

    private void runThread4() {
// Sau 2 giay thi cap nhat 1 lan
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        i4++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtThread4.setText("Thread 4 = " + i4);
                            if (i4 == 4) {
                                txtThread4.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                    if (i4 == 4) {
                        break;
                    }
                }
            }
        });
    }

    private void runThread5() {
// Sau 1 giay thi cap nhat 1 lan
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        i5++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtThread5.setText("Thread 5 = " + i5);
                            if (i5 == 8) {
                                txtThread5.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                    if (i5 == 8) {
                        break;
                    }
                }
            }
        });
    }

    private void runThread6() {
// Sau 3 giay thi cap nhat 1 lan
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        i6++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtThread6.setText("Thread 6 = " + i6);
                            if (i6 == 3) {
                                txtThread6.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                    if (i6 == 3) {
                        break;
                    }
                }
            }
        });
    }
}
