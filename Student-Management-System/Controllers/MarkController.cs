using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using StudentManagementSystem.Models.EntityFramework;
using StudentManagementSystem.Models;

namespace StudentManagementSystem.Controllers
{
    public class MarkController : Controller
    {
        // GET: Mark
        DbSchoolEntities db = new DbSchoolEntities();
        public ActionResult Index()
        {
            var marks = db.MARKS.ToList();
            return View(marks);
        }

        [HttpGet]
        public ActionResult NewMark()
        {
            return View();
        }

        [HttpPost]
        public ActionResult NewMark(MARKS mrk)
        {
            db.MARKS.Add(mrk);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Delete(int id)
        {
            var mark = db.MARKS.Find(id);
            db.MARKS.Remove(mark);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        [HttpGet]
        public ActionResult Update(int id)
        {
            var mrk = db.MARKS.Find(id);
            return View("Update", mrk);
        }

        [HttpPost]
        public ActionResult Update(Class1 model, MARKS p, int EXAM1 = 0, int EXAM2 = 0, int EXAM3 = 0, int PROJECT = 0)
        {
            if(model.operation == "Calculate")
            {
                float AVERAGE = (EXAM1 + EXAM2 + EXAM3 + PROJECT) / 4;
                ViewBag.avg = AVERAGE;
                
                if(AVERAGE < 50)
                {
                    ViewBag.sit = false;
                }
                else
                {
                    ViewBag.sit = true;
                }
            }
            else if(model.operation == "Update")
            {
                var mrk = db.MARKS.Find(p.MARKID);
                mrk.STDID = p.STDID;
                mrk.LECTUREID = p.LECTUREID;
                mrk.EXAM1 = p.EXAM1;
                mrk.EXAM2 = p.EXAM2;
                mrk.EXAM3 = p.EXAM3;
                mrk.PROJECT = p.PROJECT;
                mrk.AVERAGE = p.AVERAGE;
                mrk.SITUATION = p.SITUATION;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View();
        }
    }
}