using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.OleDb;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;


namespace DictionaryGame
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

     
        OleDbConnection connection = new OleDbConnection(@"YOUR DATABASE PATH");
        Random random = new Random();
        OleDbCommand cmd;
        int time = 60;
        int word = 0;

        void generate()
        {
            int number;
            number = random.Next(1, 2490);
            lblAnswer.Visible = false;
            connection.Open();
            cmd = new OleDbCommand("Select * from dictionary where Id = @p1", connection);
            cmd.Parameters.AddWithValue("@p1", number);
            OleDbDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                txtEN.Text = dr[1].ToString();
                lblAnswer.Text = dr[2].ToString();
                lblAnswer.Text = lblAnswer.Text.ToLower();
            }
            connection.Close();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            generate();
            timer1.Start();
        }

        private void txtTR_TextChanged(object sender, EventArgs e)
        {
            if(txtTR.Text == lblAnswer.Text)
            {
                word++;
                lblWord.Text = word.ToString();
                generate();
                txtTR.Clear();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            lblAnswer.Visible = true;
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            time--;
            lblTime.Text = time.ToString();
            if (time == 0)
            {
                timer1.Stop();
                txtTR.Enabled = false;
            }
        }
    }
}
