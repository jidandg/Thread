package unmodified;

/**
 * Anggota Kelompok 6:
 * @author 1806200116 - Jidan Dhirayoga Gumbira
 * @author 1806200280 - Vernando Wijaya Putra
 * @author 1806200330 - Glory Alifa Puncuna
 *
 * @since 2021-6-2
 */
class SavingsAccount {
    public float balance = 1000;

    /**
     * Fungsi untuk melakukan pengambilan dari rekening. Apabila nilai yang
     * diambil bernilai negatif, maka exception akan dikeluarkan. Fungsi ini
     * merupakan synchronized function.
     */
    public void withdraw(float anAmount) {
        if (anAmount < 0.0) throw new IllegalArgumentException("Withdraw amount negative");

        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing money.");
            if (anAmount <= balance) {
                balance = balance - anAmount;
            }
            System.out.println("Current balance after withdrawal by "
                    + Thread.currentThread().getName() + ": " + balance);
        }
    }

    /**
     * Fungsi untuk melakukan deposit ke rekening. Apabila nilai yang
     * dimasukkan bernilai negatif, maka exception akan dikeluarkan. Fungsi
     * merupakan synchronized function.
     */
    public void deposit(float anAmount) {
        if (anAmount < 0.0) throw new IllegalArgumentException("Deposit amount negative");

        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is depositing money");
            balance = balance + anAmount;
            System.out.println("Current balance after thread " + Thread.currentThread().getName()
                    + " has finished depositing money: " + balance);
        }
    }
}

/**
 * Kelas ini digunakan untuk melakukan deposit. Pada fungsi ini, kelas
 * akan menjalankan thread secara acak dan melakukan deposit ke rekening
 * (balance), sehingga kelas ini memiliki sifat tidak dapat diprediksi
 * (unpredictable).
 */
class Thread1 extends Thread {
    SavingsAccount savings;

    Thread1(SavingsAccount savings) {
        this.savings = savings;
    }

    public void run() {
        savings.deposit(500);
    }
}

/**
 * Kelas ini digunakan untuk melakukan penarikan. Pada fungsi ini, kelas
 * akan menjalankan thread secara acak dan melakukan penarikan dari
 * rekening, sehingga kelas ini memiliki sifat tidak dapat diprediksi.
 */
class Thread2 extends Thread {
    SavingsAccount savings;

    Thread2(SavingsAccount savings) {
        this.savings = savings;
    }

    public void run() {
        savings.withdraw(500);
    }
}

class Main {
    /**
     * Fungsi ini digunakan untuk menjalankan thread yang digunakan oleh
     * program.
     * @param args Parameter argumen, yang pada fungsi ini tidak digunakan
     */
    public static void main(String args[]) {
        SavingsAccount savings = new SavingsAccount();
        Thread1 thread1 = new Thread1(savings);
        Thread2 thread2 = new Thread2(savings);
        Thread t_1 = new Thread(thread1);
        System.out.println("T_1 : " + t_1.getName());
        Thread t_2 = new Thread(thread2);
        System.out.println("T_2 : " + t_2.getName());

        t_1.start();
        t_2.start();
    }
}
