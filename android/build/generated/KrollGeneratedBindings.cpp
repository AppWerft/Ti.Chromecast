/* C++ code produced by gperf version 3.0.3 */
/* Command-line: /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/gperf -L C++ -E -t /Users/fuerst/Documents/APPC_WORKSPACE/Ti.Chromecast/android/build/generated/KrollGeneratedBindings.gperf  */
/* Computed positions: -k'' */

#line 3 "/Users/fuerst/Documents/APPC_WORKSPACE/Ti.Chromecast/android/build/generated/KrollGeneratedBindings.gperf"


#include <string.h>
#include <v8.h>
#include <KrollBindings.h>

#include "ti.googlecast.ChromecastModule.h"
#include "ti.googlecast.RouteInfoProxy.h"


#line 14 "/Users/fuerst/Documents/APPC_WORKSPACE/Ti.Chromecast/android/build/generated/KrollGeneratedBindings.gperf"
struct titanium::bindings::BindEntry;
/* maximum key range = 3, duplicates = 0 */

class TichromecastBindings
{
private:
  static inline unsigned int hash (const char *str, unsigned int len);
public:
  static struct titanium::bindings::BindEntry *lookupGeneratedInit (const char *str, unsigned int len);
};

inline /*ARGSUSED*/
unsigned int
TichromecastBindings::hash (register const char *str, register unsigned int len)
{
  return len;
}

struct titanium::bindings::BindEntry *
TichromecastBindings::lookupGeneratedInit (register const char *str, register unsigned int len)
{
  enum
    {
      TOTAL_KEYWORDS = 2,
      MIN_WORD_LENGTH = 28,
      MAX_WORD_LENGTH = 30,
      MIN_HASH_VALUE = 28,
      MAX_HASH_VALUE = 30
    };

  static struct titanium::bindings::BindEntry wordlist[] =
    {
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""},
#line 16 "/Users/fuerst/Documents/APPC_WORKSPACE/Ti.Chromecast/android/build/generated/KrollGeneratedBindings.gperf"
      {"ti.googlecast.RouteInfoProxy",::ti::googlecast::tichromecast::RouteInfoProxy::bindProxy,::ti::googlecast::tichromecast::RouteInfoProxy::dispose},
      {""},
#line 17 "/Users/fuerst/Documents/APPC_WORKSPACE/Ti.Chromecast/android/build/generated/KrollGeneratedBindings.gperf"
      {"ti.googlecast.ChromecastModule",::ti::googlecast::ChromecastModule::bindProxy,::ti::googlecast::ChromecastModule::dispose}
    };

  if (len <= MAX_WORD_LENGTH && len >= MIN_WORD_LENGTH)
    {
      unsigned int key = hash (str, len);

      if (key <= MAX_HASH_VALUE)
        {
          register const char *s = wordlist[key].name;

          if (*str == *s && !strcmp (str + 1, s + 1))
            return &wordlist[key];
        }
    }
  return 0;
}
#line 18 "/Users/fuerst/Documents/APPC_WORKSPACE/Ti.Chromecast/android/build/generated/KrollGeneratedBindings.gperf"

