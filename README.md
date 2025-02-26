# پیمایشگر گراف
در این پروژه، یک پیمایشگر ساده گراف با دو روش اول-سطح و اول-عمق پیاده سازی شده است.

# بخش اول: پیاده‌سازی الگوی Adapter
الگوی طراحی Adapter یک روش استاندارد برای هماهنگ‌سازی دو بخش ناسازگار از کد است. در پروژه حاضر، هدف از پیاده‌سازی این الگو، تغییر وابستگی کد به کتابخانه گراف JUNG و جایگزینی آن با کتابخانه JGraphT بود، بدون اینکه ساختار کلی پروژه تغییر کند. این کار به گونه‌ای انجام شد که سایر قسمت‌های پروژه از تغییرات داخلی کتابخانه بی‌خبر باشند.

## انتخاب نوع Adapter
در این پروژه، از Object Adapter استفاده شده است. دلیل این انتخاب، انعطاف‌پذیری بیشتر این نوع از Adapter است که امکان کار با هر دو کتابخانه را به شکل مستقل فراهم می‌کند. همچنین، این نوع از Adapter از ترکیب (Composition) به جای ارث‌بری استفاده می‌کند، که این امر باعث می‌شود وابستگی‌های کمتری میان کلاس‌ها ایجاد شود و قابلیت نگهداری کد افزایش یابد.

## چرا از Class Adapter استفاده نشد؟

زیرا Class Adapter از ارث‌بری چندگانه استفاده می‌کند که در زبان Java پشتیبانی نمی‌شود.

استفاده از Class Adapter موجب افزایش وابستگی‌های مستقیم میان کلاس‌ها می‌شود و این موضوع با اهداف پروژه (استقلال بخش‌ها) در تناقض بود.

## نحوه پیاده‌سازی

برای پیاده‌سازی Adapter، مراحل زیر انجام شد:

تعریف یک واسط (Interface): ابتدا واسطی ایجاد شد که تمامی متدهای موردنیاز برای تعامل با کتابخانه گراف را مشخص می‌کند. این واسط، به عنوان نقطه ارتباطی میان پروژه و Adapter عمل می‌کند.

ایجاد کلاس Adapter: کلاسی با نام مشخص ایجاد شد که این واسط را پیاده‌سازی می‌کند. در این کلاس، از کتابخانه JGraphT به صورت داخلی استفاده شد.

تبدیل متدها: تمامی متدهای موجود در کتابخانه JUNG که در پروژه استفاده می‌شدند، معادل‌سازی شده و با متدهای مشابه در JGraphT جایگزین شدند. در این فرآیند، از Adapter برای هماهنگی فرمت داده‌ها و پارامترها استفاده شد.

استفاده در پروژه: در نهایت، بخش‌هایی از پروژه که قبلاً به کتابخانه JUNG وابسته بودند، به واسط تعریف‌شده متصل شدند. این تغییرات، بدون ایجاد تغییرات گسترده در سایر قسمت‌های پروژه اعمال شد.

# بخش دوم: تغییر کتابخانه از JUNG به JGraphT

## گزارش تغییر کتابخانه

در این پروژه، یکی از اهداف اصلی کاهش وابستگی به کتابخانه JUNG و جایگزینی آن با JGraphT بود. دلیل این تغییر، دسترسی به امکانات به‌روزتر، پشتیبانی بهتر، و مستندات جامع‌تر JGraphT است. همچنین، JGraphT به دلیل ساختار ساده‌تر و انعطاف‌پذیری بیشتر، گزینه مناسبی برای مدیریت گراف‌ها در پروژه به شمار می‌رود.

برای اعمال این تغییر، از الگوی طراحی Adapter استفاده شد تا تغییرات به صورت شفاف و بدون تأثیرگذاری مستقیم بر سایر قسمت‌های پروژه اعمال شوند.

## چگونگی تغییر کتابخانه

بررسی قابلیت‌های مشترک: ابتدا قابلیت‌های مورد استفاده از کتابخانه JUNG شناسایی شدند. سپس معادل‌های این قابلیت‌ها در JGraphT پیدا شدند و بررسی شد که آیا می‌توان آن‌ها را بدون تغییر در منطق پروژه جایگزین کرد.

تعریف واسط (Interface): یک واسط تعریف شد که متدهای ضروری برای تعامل با کتابخانه گراف را مشخص می‌کند. این واسط به عنوان نقطه ارتباطی میان پروژه و Adapter عمل می‌کند و پروژه را از جزئیات پیاده‌سازی مستقل نگه می‌دارد.

ایجاد کلاس Adapter: کلاس Adapter پیاده‌سازی شد و از JGraphT به عنوان کتابخانه داخلی استفاده کرد. این کلاس وظیفه تطابق داده‌ها، تبدیل فرمت‌ها، و ارائه متدهای موردنیاز واسط را بر عهده دارد.

اتصال کلاس‌های موجود به Adapter: بخش‌هایی از پروژه که مستقیماً به JUNG وابسته بودند، به واسط جدید متصل شدند. این تغییر باعث شد بدون تغییر در سایر بخش‌های پروژه، وابستگی به JUNG حذف شود.

## تغییرات ناشی از مهاجرت

ساختار داده‌ها: JGraphT ساختار متفاوتی برای گراف و داده‌ها ارائه می‌دهد. به همین دلیل، داده‌های پروژه از طریق Adapter به ساختار قابل قبول JGraphT تبدیل شدند. این تبدیل بدون تغییر در منطق اصلی پروژه انجام شد.

متدهای پیمایش: الگوریتم‌های BFS و DFS که قبلاً از JUNG استفاده می‌کردند، بدون تغییر باقی ماندند. زیرساخت مورد استفاده آن‌ها از طریق Adapter به کتابخانه JGraphT متصل شد، به‌گونه‌ای که وابستگی به JUNG حذف و امکان استفاده از JGraphT فراهم شد.

کاهش وابستگی: با استفاده از الگوی طراحی Adapter، پروژه به جای وابستگی مستقیم به یک کتابخانه خاص، به واسطی عمومی وابسته شد. این امر انعطاف‌پذیری بیشتری برای جایگزینی کتابخانه‌ها در آینده ایجاد می‌کند.

# بخش سوم: تحلیل الگوی استراتژی

## آیا استفاده از این الگو قابل قبول است؟ چرا؟ 

بله، استفاده از الگوی Strategy در این پروژه کاملاً قابل قبول است، چرا که تفکیک مسئولیت‌ها، انعطاف‌پذیری، و قابلیت گسترش پروژه را تضمین می‌کند.

## روش تحقق این الگو چگونه است؟ 

این الگو با تعریف یک واسط مشترک برای الگوریتم‌ها و پیاده‌سازی آن در کلاس‌های جداگانه (DFS و BFS) تحقق یافته است. کلاس Context (یعنی GraphTraverser) از این واسط استفاده می‌کند و وابستگی به الگوریتم خاصی ندارد.

